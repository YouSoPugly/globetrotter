package cc.dylanpriebe.backend.security;

import cc.dylanpriebe.backend.objects.User;
import cc.dylanpriebe.backend.repository.UserRepository;
import cc.dylanpriebe.backend.utils.ImageDownloader;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final ImageDownloader imageDownloader;

    public CustomOAuth2UserService(UserRepository userRepository, ImageDownloader imageDownloader) {
        this.userRepository = userRepository;
        this.imageDownloader = imageDownloader;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) {
        OAuth2User oAuth2User = super.loadUser(request);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        String pictureUrl = (String) attributes.get("picture");
        byte[] avatarBytes = imageDownloader.download(pictureUrl);

        User user = userRepository.findByEmail(email).orElseGet(
                () -> {
                    User newUser = new User(email, name, avatarBytes);
                    return userRepository.save(newUser);
                }
        );

        if (user.getAvatar() != avatarBytes) {
            user.setAvatar(avatarBytes);
            userRepository.save(user);
        }

        return oAuth2User;
    }

}

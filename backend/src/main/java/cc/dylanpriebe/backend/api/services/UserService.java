package cc.dylanpriebe.backend.api.services;

import cc.dylanpriebe.backend.api.dto.UserDTO;
import cc.dylanpriebe.backend.objects.DiaryEntry;
import cc.dylanpriebe.backend.objects.User;
import cc.dylanpriebe.backend.repository.DiaryEntryRepository;
import cc.dylanpriebe.backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        return userRepository.save(user);
    }

    public byte[] getAvatar(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(User::getAvatar).orElse(null);
    }

    public User getUser(OAuth2User oAuth2User) {
        return userRepository.findByEmail(oAuth2User.getAttribute("email")).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}

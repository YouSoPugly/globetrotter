package cc.dylanpriebe.backend.api.dto;

import cc.dylanpriebe.backend.objects.User;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;

public record UserDTO(long id, String name, String email, String profilePictureUrl) {

    public static UserDTO fromUser(User user) {
        String profilePictureUrl = "/api/users/" + user.getId() + "/avatar";
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), profilePictureUrl);
    }

}


package cc.dylanpriebe.backend.api.controllers;

import cc.dylanpriebe.backend.api.dto.UserDTO;
import cc.dylanpriebe.backend.api.services.UserService;
import cc.dylanpriebe.backend.objects.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/whoami")
    public UserDTO whoami(@AuthenticationPrincipal OAuth2User principal) {
        return UserDTO.fromUser(userService.getUser(principal));
    }

    @GetMapping
    public List<User> findAll() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}/avatar")
    public ResponseEntity<?> getAvatar(@PathVariable Long id) {
        byte[] avatar = userService.getAvatar(id);
        if (avatar == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok()
                .header("Content-Type", "image/jpeg")
                .body(avatar);
    }
}

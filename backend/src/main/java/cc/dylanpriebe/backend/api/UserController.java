package cc.dylanpriebe.backend.api;

import cc.dylanpriebe.backend.objects.User;
import cc.dylanpriebe.backend.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/whoami")
    public User whoami(@AuthenticationPrincipal OAuth2User user) {
        return repo.findByEmail(String.valueOf(user.getAttributes().get("email"))).orElse(null);
    }

    @GetMapping
    public List<User> findAll() {
        return repo.findAll();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return repo.save(user);
    }

}

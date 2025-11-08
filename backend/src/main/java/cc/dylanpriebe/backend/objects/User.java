package cc.dylanpriebe.backend.objects;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String name;
    private String profilePicture;

    public User() {}

    public User(String email, String name, String profilePicture) {
        this.email = email;
        this.name = name;
        this.profilePicture = profilePicture;
    }
}

package cc.dylanpriebe.backend.objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@EqualsAndHashCode(exclude = "avatar")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Lob
    private byte[] avatar;

    public User(String email, String name, byte[] avatar) {
        this.email = email;
        this.name = name;
        this.avatar = avatar;
    }
}

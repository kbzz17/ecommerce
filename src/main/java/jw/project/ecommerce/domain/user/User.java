package jw.project.ecommerce.domain.user;

import jw.project.common.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> role;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public User(String email, String password, String name, Set<Role> role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public static User register(String email, String encryptedPw, String name, Set<Role> role) {
        return new User(email, encryptedPw, name, role);
    }
}

package jw.project.ecommerce.domain.user;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    boolean ExistsByEmail(String email);

    Optional<User> findByEmail(String email);
}

package jw.project.ecommerce.domain.user;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(Long id);

    void deleteById(Long id);


    boolean ExistsByEmail(String email);

    Optional<User> findByEmail(String email);
}

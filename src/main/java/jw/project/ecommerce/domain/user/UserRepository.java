package jw.project.ecommerce.domain.user;

public interface UserRepository {
    User save(User user);

    boolean ExistsByEmail(String email);
}

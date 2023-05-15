package jw.project.ecommerce.infrastructure.user;

import jw.project.ecommerce.domain.user.User;
import jw.project.ecommerce.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final JpaUserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}

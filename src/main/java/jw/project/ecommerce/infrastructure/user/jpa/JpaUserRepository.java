package jw.project.ecommerce.infrastructure.user.jpa;

import jw.project.ecommerce.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}

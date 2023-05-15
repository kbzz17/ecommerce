package jw.project.ecommerce.application.user.command;

import jw.project.common.Role;
import jw.project.ecommerce.domain.user.User;

import java.util.Set;

public record SignupCommand(
        /**
         * [O] SignupCommand -> Entity 구현
         */
        String email,
        String password,
        String name,
        Set<Role> role
) {
    public User toEntity() {
        return new User(
                email,
                password,
                name,
                role
        );
    }
}

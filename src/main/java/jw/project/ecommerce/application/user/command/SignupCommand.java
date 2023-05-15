package jw.project.ecommerce.application.user.command;

import jw.project.ecommerce.domain.user.User;

public record SignupCommand(
        /**
         * [O] SignupCommand -> Entity 구현
         */
        String email,
        String password,
        String name
) {
    public User toEntity() {
        return new User(
                email,
                password,
                name
        );
    }
}

package jw.project.ecommerce.presentation.user.response;

import jw.project.ecommerce.domain.user.User;

public record SignupResponse(
        Long id,
        String email,
        String name
)
{
    public static SignupResponse from(User user){
        return new SignupResponse(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }
}

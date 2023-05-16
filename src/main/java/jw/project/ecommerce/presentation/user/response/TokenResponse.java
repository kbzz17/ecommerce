package jw.project.ecommerce.presentation.user.response;

public record TokenResponse (
        String accessToken,
        String RefreshToken
){
}

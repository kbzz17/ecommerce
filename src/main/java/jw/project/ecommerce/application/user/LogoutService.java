package jw.project.ecommerce.application.user;

import jw.project.ecommerce.domain.user.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {
    private final TokenRepository tokenRepository;

    public Boolean logout(String refreshToken) {
        return tokenRepository.deleteToken(refreshToken);
    }
}

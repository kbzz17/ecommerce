package jw.project.ecommerce.application.user;

import jw.project.ecommerce.application.user.command.LoginCommand;
import jw.project.ecommerce.domain.exception.EmailNotExistsException;
import jw.project.ecommerce.domain.exception.PasswordNotMatchException;
import jw.project.ecommerce.domain.user.PasswordEncryptor;
import jw.project.ecommerce.domain.user.User;
import jw.project.ecommerce.domain.user.UserRepository;
import jw.project.ecommerce.infrastructure.user.jwt.TokenGenerator;
import jw.project.ecommerce.presentation.user.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;
    private final TokenGenerator tokenGenerator;

    public TokenResponse login(LoginCommand command) {
        User user = validateLogin(command);

        String accessToken = tokenGenerator.generateAccessToken(user.getId(), user.getRole());
        String refreshToken = tokenGenerator.generateRefreshToken(user.getId(), user.getRole());

        return new TokenResponse(accessToken, refreshToken);
    }

    private User validateLogin(LoginCommand command) {
        User user = userRepository.findByEmail(command.email()).orElseThrow(() -> {
            throw new EmailNotExistsException();
        });

        if (!passwordEncryptor.CheckPassword(command.password(), user.getPassword())) {
            throw new PasswordNotMatchException();
        }
        return user;
    }
}

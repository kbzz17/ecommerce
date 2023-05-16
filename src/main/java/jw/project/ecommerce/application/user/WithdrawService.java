package jw.project.ecommerce.application.user;

import jw.project.ecommerce.application.user.command.WithdrawCommand;
import jw.project.ecommerce.domain.exception.EmailNotExistsException;
import jw.project.ecommerce.domain.exception.PasswordNotMatchException;
import jw.project.ecommerce.domain.user.PasswordEncryptor;
import jw.project.ecommerce.domain.user.TokenRepository;
import jw.project.ecommerce.domain.user.UserRepository;
import jw.project.ecommerce.infrastructure.user.jwt.AuthenticatedAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WithdrawService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;


    public Void withdraw(WithdrawCommand command, String refreshToken, AuthenticatedAccount account) {

        var user = userRepository.findByEmail(command.email()).orElseThrow(() -> {
            throw new EmailNotExistsException();
        });

        if (!passwordEncryptor.CheckPassword(command.password(), user.getPassword())) {
            throw new PasswordNotMatchException();
        }
        userRepository.deleteById(account.id());
        tokenRepository.deleteToken(refreshToken);
        return null;
    }
}

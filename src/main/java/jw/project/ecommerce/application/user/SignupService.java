package jw.project.ecommerce.application.user;

import jw.project.ecommerce.application.user.command.SignupCommand;
import jw.project.ecommerce.domain.exception.DuplicatedEmailException;
import jw.project.ecommerce.domain.user.PasswordEncryptor;
import jw.project.ecommerce.domain.user.User;
import jw.project.ecommerce.domain.user.UserRepository;
import jw.project.ecommerce.presentation.user.response.SignupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupService {
    private final UserRepository userRepository;
    private final PasswordEncryptor passwordEncryptor;

    public SignupResponse signup(SignupCommand command) {
        /**
         * [O] Parameter : SignupCommand 구현
         * [O] User Entity 구현
         * [O] User JpaRepository 구현
         * [O] 가입 시 비밀번호 암호화 구현
         */
        if (userRepository.ExistsByEmail(command.email())) {
            throw new DuplicatedEmailException();
        }
        String encryptedPassword = passwordEncryptor.encoder(command.password());

        User user = User.register(command.email(), encryptedPassword, command.name(), command.role());
        userRepository.save(user);

        return SignupResponse.from(user);
    }
}

package jw.project.ecommerce.application.User;

import jw.project.common.Role;
import jw.project.ecommerce.application.user.LoginService;
import jw.project.ecommerce.application.user.SignupService;
import jw.project.ecommerce.application.user.command.LoginCommand;
import jw.project.ecommerce.application.user.command.SignupCommand;
import jw.project.ecommerce.domain.exception.EmailNotExistsException;
import jw.project.ecommerce.domain.exception.PasswordNotMatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;

@SpringBootTest
@Transactional
public class LoginTest {
    @Autowired
    public LoginService loginService;
    @Autowired
    public SignupService signupService;

    @Test
    @DisplayName("가입되지 않은 email 입력 시 예외 발생")
    void loginWithWrongEmail() {
        LoginCommand command = new LoginCommand("nobodyuse@naver.com", "1234123412");
        Assertions.assertThrows(EmailNotExistsException.class, () -> loginService.login(command));
    }

    @Test
    @DisplayName("잘못된 비밀번호 입력 시 예외 발생")
    void loginWithWrongPassword() {
        String email = "anyoneuse@naver.com";
        String password = "1234123412";
        String wrongPassword = "4321432143";

        SignupCommand signupCommand = new SignupCommand(email, password, "yjw", new HashSet<>(Collections.singleton(Role.USER)));
        LoginCommand loginCommand = new LoginCommand(email, wrongPassword);

        signupService.signup(signupCommand);

        Assertions.assertThrows(PasswordNotMatchException.class, () -> loginService.login(loginCommand));
    }


}

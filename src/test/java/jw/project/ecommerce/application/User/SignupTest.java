package jw.project.ecommerce.application.User;

import jw.project.common.Role;
import jw.project.ecommerce.application.user.SignupService;
import jw.project.ecommerce.application.user.command.SignupCommand;
import jw.project.ecommerce.domain.exception.DuplicatedEmailException;
import jw.project.ecommerce.domain.user.UserRepository;
import jw.project.ecommerce.presentation.user.request.SignupRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

@SpringBootTest
@Transactional
public class SignupTest {

    @Autowired
    public Validator validatorInjected;
    @Autowired
    public SignupService signupService;
    @Mock
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("SignupRequest Validation 테스트")
    void SignupRequest_Validation_Test() {
        Set<Role> role = new HashSet<>();
        role.add(Role.USER);
        // given
        SignupRequest request = new SignupRequest("poujisnaver.com", "1234", "", role);
        // when
        Set<ConstraintViolation<SignupRequest>> validate = validatorInjected.validate(request);
        // then

        Iterator<ConstraintViolation<SignupRequest>> iterator = validate.iterator();
        List<String> messages = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<SignupRequest> next = iterator.next();
            messages.add(next.getMessage());
        }

        Assertions.assertThat(messages).contains("8~20글자 사이로 입력해 주세요", "잘못된 이메일 형식입니다.", "이름을 입력해 주세요");
    }

    @Test
    @DisplayName("회원가입 이메일 중복 검사")
    void DuplicatedValidation() {
        Set<Role> role = new HashSet<>();
        role.add(Role.USER);
        //given
        SignupCommand command = new SignupCommand("poujis2@naver.com", "1234123412", "yjw", role);

        //when
        userRepository.save(command.toEntity());

        //then
        Assertions.assertThatThrownBy(() -> signupService.signup(command)).isInstanceOf(DuplicatedEmailException.class);
    }
}

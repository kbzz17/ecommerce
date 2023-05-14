package jw.project.ecommerce.presentation.user;

import jw.project.ecommerce.application.user.SignupService;
import jw.project.ecommerce.presentation.user.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final SignupService signupService;

    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignupRequest request) {
        /**
         * [O] Parameter : SignupRequest
         * [O] SignupService 구현 및 회원 가입 메소드 호출
         * [ ] 공통 Response 구현
         */
        signupService.signup(request.toCommand());
    }

    @PostMapping("/login")
    public void login() {
        /**
         * [ ] Parameter : LoginRequest
         * [ ] LoginService 구현 및 로그인 메소드 호출
         * [ ] 공통 Response 구현
         */
    }

    @PostMapping("/logout")
    public void logout() {
        /**
         * [ ] Parameter : LogoutRequest
         * [ ] LogoutService 구현 및 호출
         * [ ] 공통 Response 구현
         */
    }

    @DeleteMapping("/withdraw")
    public void withdraw() {
        /**
         *  [ ] Parameter : WithdrawRequest
         *  [ ] WithdrawService 구현 및 호출
         *  [ ] 공통 Response 구현
         */
    }

}

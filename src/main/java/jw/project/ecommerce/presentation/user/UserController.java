package jw.project.ecommerce.presentation.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/signup")
    public void signup() {
        /**
         * [ ] Parameter : SignupRequest
         * [ ] SignupService 구현 및 회원 가입 메소드 호출
         * [ ] 공통 Response 구현
         */
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
    public void withdraw(){
        /**
         *  [ ] Parameter : WithdrawRequest
         *  [ ] WithdrawService 구현 및 호출
         *  [ ] 공통 Response 구현
         */
    }

}

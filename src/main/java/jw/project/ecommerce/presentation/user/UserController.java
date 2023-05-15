package jw.project.ecommerce.presentation.user;

import jw.project.common.ApiResponse;
import jw.project.ecommerce.application.user.LoginService;
import jw.project.ecommerce.application.user.SignupService;
import jw.project.ecommerce.presentation.user.request.LoginRequest;
import jw.project.ecommerce.presentation.user.request.SignupRequest;
import jw.project.ecommerce.presentation.user.response.SignupResponse;
import jw.project.ecommerce.presentation.user.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final SignupService signupService;
    private final LoginService loginService;

    @PostMapping("/signup")
    public ApiResponse<SignupResponse> signup(@Valid @RequestBody SignupRequest request) {
        /**
         * [O] Parameter : SignupRequest
         * [O] SignupService 구현 및 회원 가입 메소드 호출
         * [O] 공통 Response 구현
         */
        SignupResponse response = signupService.signup(request.toCommand());
        return ApiResponse.success(response);
    }

    @PostMapping("/login")
    public ApiResponse<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        /**
         * [O] Parameter : LoginRequest
         * [O] LoginService 구현 및 로그인 메소드 호출
         * [O] 공통 Response 구현
         */
        TokenResponse response = loginService.login(request.toCommand());
        return ApiResponse.success(response);
    }

    @PostMapping("/logout")
    public ApiResponse<?> logout() {
        /**
         * [ ] Parameter : LogoutRequest
         * [ ] LogoutService 구현 및 호출
         * [O] 공통 Response 구현
         */
        return null;
    }

    @DeleteMapping("/withdraw")
    public ApiResponse<?> withdraw() {
        /**
         *  [ ] Parameter : WithdrawRequest
         *  [ ] WithdrawService 구현 및 호출
         *  [O] 공통 Response 구현
         */
        return null;
    }

}

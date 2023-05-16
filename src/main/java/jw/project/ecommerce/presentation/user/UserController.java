package jw.project.ecommerce.presentation.user;

import jw.project.common.ApiResponse;
import jw.project.ecommerce.application.user.LoginService;
import jw.project.ecommerce.application.user.LogoutService;
import jw.project.ecommerce.application.user.SignupService;
import jw.project.ecommerce.application.user.WithdrawService;
import jw.project.ecommerce.infrastructure.user.jwt.AuthenticatedAccount;
import jw.project.ecommerce.presentation.user.request.LoginRequest;
import jw.project.ecommerce.presentation.user.request.SignupRequest;
import jw.project.ecommerce.presentation.user.request.WithdrawRequest;
import jw.project.ecommerce.presentation.user.response.SignupResponse;
import jw.project.ecommerce.presentation.user.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final SignupService signupService;
    private final LoginService loginService;
    private final LogoutService logoutService;
    private final WithdrawService withdrawService;

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

    @GetMapping("/logout")
    public ApiResponse<?> logout(@RequestHeader("RefreshToken") String refreshToken) {
        /**
         * [O] Parameter : RefreshToken
         * [O] LogoutService 구현 및 호출
         * [O] 공통 Response 구현
         */
        return ApiResponse.success(logoutService.logout(refreshToken));
    }

    @DeleteMapping("/withdraw/{id}")
    public ApiResponse<?> withdraw(WithdrawRequest request, @RequestHeader("RefreshToken") String refreshToken, @AuthenticationPrincipal AuthenticatedAccount user) {
        withdrawService.withdraw(request.toCommand(), refreshToken, user);
        return ApiResponse.success(null);
    }

}

package jw.project.ecommerce.presentation.user.request;

import jw.project.ecommerce.application.user.command.WithdrawCommand;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record WithdrawRequest(
        /**
         * [O] Validation 구현
         *      email : 이메일 형식에 맞는지
         *              빈칸 금지
         *      password : 빈칸 금지
         * [O] toCommand() 구현 : 책임 관계를 명확하게 하기 위함
         */
        @NotBlank(message = "아이디를 입력해 주세요")
        @Email(message = "잘못된 이메일 형식입니다.")
        String email,
        @NotBlank(message = "패스워드를 입력해 주세요")
        @Size(min = 8, max = 20, message = "8~20글자 사이로 입력해 주세요")
        String password
) {
    public WithdrawCommand toCommand() {
        return new WithdrawCommand(
                this.email,
                this.password
        );
    }
}

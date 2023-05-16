package jw.project.ecommerce.application.user.command;

public record WithdrawCommand(
        String email,
        String password
) {
}

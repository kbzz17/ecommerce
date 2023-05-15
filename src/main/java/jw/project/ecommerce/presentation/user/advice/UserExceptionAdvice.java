package jw.project.ecommerce.presentation.user.advice;

import jw.project.common.ApiResponse;
import jw.project.common.ErrorCode;
import jw.project.common.ServerException;
import jw.project.ecommerce.domain.exception.DuplicatedEmailException;
import jw.project.ecommerce.presentation.user.UserController;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice(assignableTypes = {UserController.class})
public class UserExceptionAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String defaultMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return ApiResponse.fail(ErrorCode.INVALID_INPUT, defaultMessage);
    }

    @ExceptionHandler({
            DuplicatedEmailException.class
    })
    public ApiResponse<?> handleUserException(ServerException ex) {
        return ApiResponse.fail(ex.getError());
    }
}

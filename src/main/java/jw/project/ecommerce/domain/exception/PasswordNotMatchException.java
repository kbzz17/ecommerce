package jw.project.ecommerce.domain.exception;

import jw.project.common.ServerException;

import static jw.project.common.ErrorCode.*;

public class PasswordNotMatchException extends ServerException {
    public PasswordNotMatchException() {
        super(PASSWORD_NOT_MATCH);
    }
}

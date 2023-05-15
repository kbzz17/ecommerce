package jw.project.ecommerce.domain.exception;

import jw.project.common.ServerException;

import static jw.project.common.ErrorCode.*;

public class EmailNotExistsException extends ServerException {
    public EmailNotExistsException() {
        super(EMAIL_NOT_EXIST);
    }
}

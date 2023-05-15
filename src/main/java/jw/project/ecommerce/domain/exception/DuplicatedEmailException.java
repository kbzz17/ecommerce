package jw.project.ecommerce.domain.exception;

import jw.project.common.ServerException;

import static jw.project.common.ErrorCode.DUPLICATED_EMAIL;

public class DuplicatedEmailException extends ServerException {

    public DuplicatedEmailException() {
        super(DUPLICATED_EMAIL);
    }
}

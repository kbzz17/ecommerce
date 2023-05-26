package jw.project.ecommerce.domain.shop.Exception;

import static jw.project.common.ErrorCode.NOT_EXISTS_SHOP;

import jw.project.common.ServerException;

public class NotExistsShopException extends ServerException {

    public NotExistsShopException() {
        super(NOT_EXISTS_SHOP);
    }
}

package jw.project.ecommerce.domain.shop.Exception;

import static jw.project.common.ErrorCode.*;

import jw.project.common.ServerException;

public class ShopAlreadyOpenException extends ServerException {

    public ShopAlreadyOpenException() {
        super(SHOP_ALREADY_OPEN);
    }
}

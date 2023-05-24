package jw.project.ecommerce.domain.shop.Exception;

import static jw.project.common.ErrorCode.SHOP_ALREADY_CLOSED;

import jw.project.common.ServerException;

public class ShopAlreadyClosedException extends ServerException {

    public ShopAlreadyClosedException() {
        super(SHOP_ALREADY_CLOSED);
    }
}

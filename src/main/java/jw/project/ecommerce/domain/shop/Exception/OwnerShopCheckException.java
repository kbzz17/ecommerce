package jw.project.ecommerce.domain.shop.Exception;

import static jw.project.common.ErrorCode.SHOP_OWNER_NOT_MATCH;

import jw.project.common.ServerException;

public class OwnerShopCheckException extends ServerException {
    public OwnerShopCheckException() {
        super(SHOP_OWNER_NOT_MATCH);
    }

}

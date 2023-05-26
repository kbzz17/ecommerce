package jw.project.ecommerce.presentation.shop.response;

import jw.project.ecommerce.domain.shop.Shop;
import jw.project.ecommerce.domain.shop.enumeration.OperateCondition;
import lombok.Getter;

public record AddShopResponse(Long id, Long ownerId, String shopName, String tel, String description,
                              String operateTime, OperateCondition operateCondition) {

    public static AddShopResponse from(Shop response) {
        return new AddShopResponse(
                response.getId(),
                response.getOwnerId(),
                response.getShopName(),
                response.getTel(),
                response.getDescription(),
                response.getOperateTime(),
                response.getOperateCondition()
        );
    }
}

package jw.project.ecommerce.presentation.shop.response;

import jw.project.ecommerce.domain.shop.Shop;

import java.util.List;

public record SearchMyShopResponse(List<Shop> shopList) {

    public static SearchMyShopResponse from(List<Shop> response) {
        return new SearchMyShopResponse(
            response
        );
    }
}

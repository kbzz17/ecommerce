package jw.project.ecommerce.presentation.shop;

import jw.project.common.ApiResponse;
import jw.project.ecommerce.application.shop.ShopService;
import jw.project.ecommerce.domain.shop.Shop;
import jw.project.ecommerce.presentation.shop.request.AddShopRequest;
import jw.project.ecommerce.presentation.shop.response.AddShopResponse;
import jw.project.ecommerce.presentation.shop.response.SearchMyShopResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping("/addShop")
    public ApiResponse<?> addShop(Long ownerId, @RequestBody AddShopRequest request) {
        Shop shop = shopService.addShop(request);
        return ApiResponse.success(AddShopResponse.from(shop));
    }

    @PostMapping("/getShop")
    public ApiResponse<?> getShop(Long shopId) {
        return ApiResponse.success(shopService.getShop(shopId));
    }

    @GetMapping("/myShops")
    public ApiResponse<?> myShops(Long ownerId) {
        List<Shop> shops = shopService.searchMyShops(ownerId);
        return ApiResponse.success(SearchMyShopResponse.from(shops));
    }

    @PostMapping("/close")
    public ApiResponse<?> closeShop(Long shopId) {
        return ApiResponse.success(shopService.closeShop(shopId));
    }

    @PostMapping("/open")
    public ApiResponse<?> openShop(Long shopId) {
        return ApiResponse.success(shopService.openShop(shopId));
    }

}

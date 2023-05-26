package jw.project.ecommerce.application.shop;

import jw.project.ecommerce.domain.shop.Exception.NotExistsShopException;
import jw.project.ecommerce.domain.shop.Exception.OwnerShopCheckException;
import jw.project.ecommerce.domain.shop.Exception.ShopAlreadyClosedException;
import jw.project.ecommerce.domain.shop.Exception.ShopAlreadyOpenException;
import jw.project.ecommerce.domain.shop.Shop;
import jw.project.ecommerce.domain.shop.enumeration.OperateCondition;
import jw.project.ecommerce.infrastructure.shop.ShopRepository;
import jw.project.ecommerce.presentation.shop.request.AddShopRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public Shop addShop(AddShopRequest request) {
        Shop shop = Shop.register(request);
        return shopRepository.save(shop);
    }

    public Shop getShop(Long shopId, Long ownerId) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> {
            throw new NotExistsShopException();
        });
        if (!shop.getOwnerId().equals(ownerId)) {
            throw new OwnerShopCheckException();
        }
        return shop;
    }

    public List<Shop> searchMyShops(Long ownerId) {
        return shopRepository.findByOwnerId(ownerId);
    }

    public Shop closeShop(Long shopId) {
        var shop = shopRepository.findById(shopId).orElseThrow(() -> {
            throw new NotExistsShopException();
        });
        if (shop.getOperateCondition() == OperateCondition.CLOSED) {
            throw new ShopAlreadyClosedException();
        }
        shop.closeShop();

        return shopRepository.save(shop);
    }

    public Shop openShop(Long shopId) {
        var shop = shopRepository.findById(shopId).orElseThrow(() -> {
            throw new NotExistsShopException();
        });

        if (shop.getOperateCondition() == OperateCondition.OPEN) {
            throw new ShopAlreadyOpenException();
        }
        shop.openShop();

        return shopRepository.save(shop);
    }
}

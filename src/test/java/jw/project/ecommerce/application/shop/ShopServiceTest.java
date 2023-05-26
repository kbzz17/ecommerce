package jw.project.ecommerce.application.shop;

import static org.assertj.core.api.Assertions.*;

import jw.project.ecommerce.domain.shop.Exception.OwnerShopCheckException;
import jw.project.ecommerce.domain.shop.Exception.ShopAlreadyClosedException;
import jw.project.ecommerce.domain.shop.Exception.ShopAlreadyOpenException;
import jw.project.ecommerce.domain.shop.Shop;
import jw.project.ecommerce.infrastructure.shop.ShopRepository;
import jw.project.ecommerce.presentation.shop.request.AddShopRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ShopServiceTest {

    @Autowired
    public ShopService shopService;
    @Autowired
    public ShopRepository shopRepository;

    @Test
    @DisplayName("입점 테스트")
    void Add_Shop_Success_Test() {
        // given
        AddShopRequest request = new AddShopRequest(1L, "ChickenMania", "02-1234-5678",
            "Delicious Chicken", "09:00~18:00");

        // when
        Shop insertedShop = shopService.addShop(request);

        // then
        assertThat(insertedShop)
            .extracting("ownerId", "shopName", "tel", "description", "operateTime")
            .contains(1L, "ChickenMania", "02-1234-5678", "Delicious Chicken", "09:00~18:00");
    }

    @Test
    @DisplayName("Shop Id를 이용해 해당 상점 정보 가져오기 테스트")
    void Get_Shop_Success_Test() {
        // given
        AddShopRequest request = new AddShopRequest(1L, "ChickenMania", "02-1234-5678",
            "Delicious Chicken", "09:00~18:00");

        // when
        Shop insertedShop = shopService.addShop(request);
        Shop myShop = shopService.getShop(1L, 1L);

        // then
        assertThat(myShop)
            .extracting("ownerId", "shopName", "tel", "description", "operateTime")
            .contains(1L, "ChickenMania", "02-1234-5678", "Delicious Chicken", "09:00~18:00");
    }

    @Test
    @DisplayName("검색하고자 하는 상점의 주인이 요청을 보낸 상점 주인과 같지 않으면 에러")
    void Get_Shop_By_OwnerId_Test() {
        // given
        AddShopRequest request = new AddShopRequest(1L, "ChickenMania", "02-1234-5678",
            "Delicious Chicken", "09:00~18:00");

        // when
        Shop insertedShop = shopService.addShop(request);

        // then
        assertThatThrownBy(() -> shopService.getShop(1L, 2L))
            .isInstanceOf(OwnerShopCheckException.class);
    }

    @Test
    @DisplayName("열고자 하는 상점이 이미 열려있는 상태면 에러 발생")
    void Shop_Already_Open_Test() {
        // given
        AddShopRequest request = new AddShopRequest(1L, "ChickenMania", "02-1234-5678",
            "Delicious Chicken", "09:00~18:00");
        Shop register = shopService.addShop(request);
        register.openShop();
        shopRepository.save(register);

        // when
        // then
        assertThatThrownBy(() -> shopService.openShop(1L))
            .isInstanceOf(ShopAlreadyOpenException.class);
    }

    @Test
    @DisplayName("닫고자 하는 상점이 이미 닫힌 상태면 에러 발생")
    void Shop_Already_Closed_Test() {
        // given
        AddShopRequest request = new AddShopRequest(1L, "ChickenMania", "02-1234-5678",
            "Delicious Chicken", "09:00~18:00");
        Shop register = shopService.addShop(request);

        // when
        // then
        assertThatThrownBy(() -> shopService.closeShop(1L))
            .isInstanceOf(ShopAlreadyClosedException.class);
    }
}

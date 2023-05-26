package jw.project.ecommerce.application.shop;

import jw.project.ecommerce.application.Support.ValidationSupport;
import jw.project.ecommerce.presentation.shop.request.AddShopRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootTest
@Transactional
public class addShopRequestValidationTest {
    @Autowired
    public ValidationSupport validationSupport;

    @Test
    @DisplayName("addShopRequest Validation 성공 테스트")
    void ShopRequest_Validation_Success_Test() {
        // given
        AddShopRequest request = new AddShopRequest(1L, "ChickenMania", "02-1234-5678", "Delicious Chicken", "09:00~18:00");
        // when
        List<String> messages = validationSupport.getValidationMessage(request);
        // then
        Assertions.assertThat(messages.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("addShopRequest Validation Null 테스트")
    void ShopRequest_Validation_Null_Test() {
        // given
        AddShopRequest request = new AddShopRequest(null, null, null, null, null);
        // when
        List<String> messages = validationSupport.getValidationMessage(request);
        // then
        Assertions.assertThat(messages).contains("잘못된 ID 입니다.", "매장 이름을 입력해주세요.","대표 전화번호를 입력해주세요.", "매장 소개를 입력해주세요.", "영업 시간을 입력해주세요.");
    }

    @Test
    @DisplayName("addShopRequest Validation Size 및 Pattern 테스트")
    void ShopRequest_Validation_Size_Pattern_Test() {
        // given
        AddShopRequest request = new AddShopRequest(1L, "", "01012345678", "", "09:00~18:00");
        // when
        List<String> messages = validationSupport.getValidationMessage(request);
        // then
        Assertions.assertThat(messages).contains("1~20글자 사이로 입력해 주세요", "유효한 전화번호를 입력해주세요.","1~200글자 사이로 입력해 주세요");
    }
}

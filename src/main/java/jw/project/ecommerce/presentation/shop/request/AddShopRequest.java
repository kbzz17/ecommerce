package jw.project.ecommerce.presentation.shop.request;

import lombok.Getter;

import javax.validation.constraints.*;

public record AddShopRequest(
        @NotNull(message = "잘못된 ID 입니다.")
        Long ownerId,
        @NotBlank(message = "매장 이름을 입력해주세요.")
        @Size(min = 1, max = 20, message = "1~20글자 사이로 입력해 주세요")
        String shopName,
        @NotBlank(message = "대표 전화번호를 입력해주세요.")
        @Pattern(regexp = "^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4]|7[1-6]|8[1-9]|9\\d)|01[1-9])-\\d{3,4}-\\d{4}$", message = "유효한 전화번호를 입력해주세요.")
        String tel,
        @NotBlank(message = "매장 소개를 입력해주세요.")
        @Size(min = 1, max = 20, message = "1~200글자 사이로 입력해 주세요")
        String description,
        @NotBlank(message = "영업 시간을 입력해주세요.")
        String operateTime
) {
}

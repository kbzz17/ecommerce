package jw.project.ecommerce.domain.shop;


import jw.project.ecommerce.domain.shop.enumeration.OperateCondition;
import jw.project.ecommerce.presentation.shop.request.AddShopRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

import static jw.project.ecommerce.domain.shop.enumeration.OperateCondition.*;

@Entity
@Table(name = "SHOP")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ownerId;
    private String shopName;
    private String tel;
    private String description;
    private String operateTime;
    @Enumerated(value = EnumType.STRING)
    private OperateCondition operateCondition;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public void closeShop() {
        this.operateCondition = CLOSED;
    }

    public void openShop() {
        this.operateCondition = OPEN;
    }

    private Shop(Long ownerId, String shopName, String tel, String description,
        String operateTime) {
        this.ownerId = ownerId;
        this.shopName = shopName;
        this.tel = tel;
        this.description = description;
        this.operateTime = operateTime;
        this.operateCondition = CLOSED;
    }

    public static Shop register(AddShopRequest request) {
        return new Shop(
            request.ownerId(),
            request.shopName(),
            request.tel(),
            request.description(),
            request.operateTime()
        );
    }
}

package jw.project.ecommerce.infrastructure.shop;

import jw.project.ecommerce.domain.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findByOwnerId(Long ownerId);
}

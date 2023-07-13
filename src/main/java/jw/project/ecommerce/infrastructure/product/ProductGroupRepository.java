package jw.project.ecommerce.infrastructure.product;

import java.util.List;
import jw.project.ecommerce.domain.product.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {

    List<ProductGroup> findByShopId(Long shopId);
}

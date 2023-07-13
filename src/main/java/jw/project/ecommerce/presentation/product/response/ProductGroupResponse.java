package jw.project.ecommerce.presentation.product.response;

import java.util.List;
import jw.project.ecommerce.domain.product.Product;
import jw.project.ecommerce.domain.product.ProductGroup;

public record ProductGroupResponse(String name, String content, Integer priority, List<Product> productList) {

    public static ProductGroupResponse from(ProductGroup productGroup) {
        return new ProductGroupResponse(
            productGroup.getName(),
            productGroup.getContent(),
            productGroup.getPriority(),
            productGroup.getProductList()
        );
    }
}

package jw.project.ecommerce.presentation.product.request.Product;

import jw.project.ecommerce.domain.product.Product;

public record UpdateProductRequest(String name, Integer price, String description) {

    public Product toEntity() {
        return Product.builder()
            .name(name)
            .price(price)
            .description(description)
            .build();
    }
}

package jw.project.ecommerce.presentation.product.response;

import jw.project.ecommerce.domain.product.Product;

public record ProductResponse(String name, Integer price, String description) {

    public static ProductResponse from(Product product) {
        return new ProductResponse(
            product.getName(),
            product.getPrice(),
            product.getDescription());
    }
}

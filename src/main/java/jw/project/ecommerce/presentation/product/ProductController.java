package jw.project.ecommerce.presentation.product;

import jw.project.common.ApiResponse;
import jw.project.ecommerce.application.product.ProductService;
import jw.project.ecommerce.domain.product.Product;
import jw.project.ecommerce.presentation.product.request.Product.AddProductRequest;
import jw.project.ecommerce.presentation.product.request.Product.UpdateProductRequest;
import jw.project.ecommerce.presentation.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/{productId}")
    public ApiResponse<?> getProductInfo(@PathVariable Long productId) {
        Product product = productService.getProductInfo(productId);
        return ApiResponse.success(product);
    }

    @PostMapping("/productGroup/{productGroupId}/product/add")
    public ApiResponse<?> addProduct(@PathVariable Long productGroupId,
        @RequestBody AddProductRequest request) {
        ProductResponse addProductResponse = productService.addProduct(productGroupId, request);
        return ApiResponse.success(addProductResponse);
    }

    @DeleteMapping("/product/delete/{productId}")
    public ApiResponse<?> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ApiResponse.success(null);
    }

    @PatchMapping("/product/update/{productId}")
    public ApiResponse<?> updateProduct(@PathVariable Long productId,
        UpdateProductRequest request) {
        ProductResponse productResponse = productService.updateProduct(productId, request);
        return ApiResponse.success(productResponse);
    }
}

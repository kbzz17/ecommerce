package jw.project.ecommerce.application.product;

import jw.project.ecommerce.domain.product.Product;
import jw.project.ecommerce.domain.product.ProductGroup;
import jw.project.ecommerce.infrastructure.product.ProductGroupRepository;
import jw.project.ecommerce.infrastructure.product.ProductRepository;
import jw.project.ecommerce.presentation.product.request.Product.AddProductRequest;
import jw.project.ecommerce.presentation.product.request.Product.UpdateProductRequest;
import jw.project.ecommerce.presentation.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductGroupRepository productGroupRepository;

    public Product getProductInfo(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> {
            throw new RuntimeException();
        });
    }

    public ProductResponse addProduct(Long productGroupId, AddProductRequest request) {
        Product product = request.toEntity();
        ProductGroup productGroup = productGroupRepository.findById(productGroupId)
            .orElseThrow(() -> {
                    throw new RuntimeException();
                }
            );
        product.assignGroup(productGroup);
        return ProductResponse.from(productRepository.save(product));
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public ProductResponse updateProduct(Long productId, UpdateProductRequest request) {
        Product product = productRepository.findById(productId).orElseThrow(() -> {
            throw new RuntimeException();
        });

        product.update(request);
        return ProductResponse.from(productRepository.save(product));
    }
}

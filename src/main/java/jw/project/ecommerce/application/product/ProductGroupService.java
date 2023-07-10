package jw.project.ecommerce.application.product;

import jw.project.ecommerce.application.shop.ShopService;
import jw.project.ecommerce.domain.product.ProductGroup;
import jw.project.ecommerce.domain.shop.Exception.OwnerShopCheckException;
import jw.project.ecommerce.infrastructure.product.ProductGroupRepository;
import jw.project.ecommerce.presentation.product.request.AddProductGroupRequest;
import jw.project.ecommerce.presentation.product.request.UpdateProductGroupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductGroupService {

    private final ShopService shopService;

    private final ProductGroupRepository productGroupRepository;

    public ProductGroup addProductGroup(Long ownerId, AddProductGroupRequest request) {
        if (!shopService.ownerShopCheck(request.getShopId(), ownerId)) {
            throw new OwnerShopCheckException();
        }
        ProductGroup productGroup = ProductGroup.register(request);

        return productGroupRepository.save(productGroup);
    }


    public void deleteGroup(Long ownerId, Long shopId, Long groupId) {
        validateOwnerShopCheck(ownerId, shopId);
        productGroupRepository.deleteById(groupId);
    }


    public ProductGroup updateGroup(Long ownerId, UpdateProductGroupRequest request) {
        validateOwnerShopCheck(request.getShopId(), ownerId);

        ProductGroup productGroup = productGroupRepository.findById(request.getGroupId())
            .orElseThrow(() -> {
                throw new RuntimeException();
            });

        productGroup.update(request.getName(), request.getContent(), request.getPriority());
        return productGroupRepository.save(productGroup);
    }

    private void validateOwnerShopCheck(Long ownerId, Long shopId) {
        if (!shopService.ownerShopCheck(shopId, ownerId)) {
            throw new OwnerShopCheckException();
        }
    }
}

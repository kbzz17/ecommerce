package jw.project.ecommerce.presentation.product;

import java.util.List;
import javax.validation.Valid;
import jw.project.common.ApiResponse;
import jw.project.ecommerce.application.product.ProductGroupService;
import jw.project.ecommerce.domain.product.ProductGroup;
import jw.project.ecommerce.presentation.product.request.AddProductGroupRequest;
import jw.project.ecommerce.presentation.product.request.UpdateProductGroupRequest;
import jw.project.ecommerce.presentation.product.response.ProductGroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-group")
public class ProductGroupController {

    private final ProductGroupService productGroupService;

    @GetMapping("/search-group/{shopId}/{ownerId}")
    public ApiResponse<?> searchGroupInfo(@PathVariable Long ownerId, @PathVariable Long shopId) {
        List<ProductGroupResponse> productResponses =
            productGroupService.searchGroupInfo(shopId, ownerId);
        return ApiResponse.success(productResponses);
    }

    @PostMapping("/add-group")
    public ApiResponse<?> addGroup(Long ownerId,
        @Valid @RequestBody AddProductGroupRequest request) {
        ProductGroup productGroup = productGroupService.addProductGroup(ownerId, request);
        return ApiResponse.success(productGroup);
    }

    @PatchMapping("/update-group")
    public ApiResponse<?> updateGroup(Long ownerId,
        @Valid @RequestBody UpdateProductGroupRequest request) {
        ProductGroup productGroup = productGroupService.updateGroup(ownerId, request);

        return ApiResponse.success(productGroup);
    }


}

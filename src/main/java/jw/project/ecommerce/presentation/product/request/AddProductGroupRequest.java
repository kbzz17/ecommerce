package jw.project.ecommerce.presentation.product.request;

import lombok.Getter;

@Getter
public class AddProductGroupRequest {
    private Long shopId;
    private String name;
    private String content;
    private Integer priority;
}

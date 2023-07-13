package jw.project.ecommerce.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import jw.project.ecommerce.presentation.product.request.AddProductGroupRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ProductGroup")
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Long shopId;
    private String name;
    private String content;
    private Integer priority;

    @OneToMany(mappedBy = "productGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> productList;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public ProductGroup(Long shopId, String name, String content, Integer priority) {
        this.shopId = shopId;
        this.name = name;
        this.content = content;
        this.priority = priority;
    }

    public static ProductGroup register(AddProductGroupRequest request) {
        return new ProductGroup(
            request.getShopId(),
            request.getName(),
            request.getContent(),
            request.getPriority());
    }

    public void update(String name, String content, Integer priority) {
        this.name = name;
        this.content = content;
        this.priority = priority;
    }
}

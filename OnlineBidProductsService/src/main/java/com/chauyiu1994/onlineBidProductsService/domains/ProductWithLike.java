package com.chauyiu1994.onlineBidProductsService.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithLike extends Product {

    @Column("PRODUCT_LIKE_ID")
    private Long productLikeId;
}

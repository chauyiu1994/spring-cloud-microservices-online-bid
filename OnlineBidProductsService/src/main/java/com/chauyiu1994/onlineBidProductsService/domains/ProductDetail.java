package com.chauyiu1994.onlineBidProductsService.domains;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("PRODUCT_DETAIL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {

    @Id
    @Column("ID")
    private Long id;

    @Column("PRODUCT_ID")
    private Long productId;

    @Column("POSITION")
    private Integer position;

    @Column("DESCRIPTION")
    private String description;

    @Column("IMAGE_URL")
    private String imageURL;

    @Column("LAST_MODIFIED_TIME")
    private LocalDateTime lastModifiedTime;
}

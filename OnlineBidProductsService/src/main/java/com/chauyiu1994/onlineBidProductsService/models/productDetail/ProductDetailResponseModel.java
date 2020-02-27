package com.chauyiu1994.onlineBidProductsService.models.productDetail;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponseModel {

    private String id;
    private String position;
    private Long productId;
    private String description;
    private String imageURL;
    private LocalDateTime lastModifiedTime;

    private String productDetailURL;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getLastModifiedTime() {
        return this.lastModifiedTime;
    }
}

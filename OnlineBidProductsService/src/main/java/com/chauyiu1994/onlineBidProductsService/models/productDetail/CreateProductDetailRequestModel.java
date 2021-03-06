package com.chauyiu1994.onlineBidProductsService.models.productDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDetailRequestModel {

    private String description;
    private String imageURL;
}

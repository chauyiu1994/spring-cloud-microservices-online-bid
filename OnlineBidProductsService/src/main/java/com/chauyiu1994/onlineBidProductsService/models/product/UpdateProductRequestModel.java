package com.chauyiu1994.onlineBidProductsService.models.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequestModel {

    private String name;
    private Float price;
    private String description;
    private String imageURL;

    private boolean incrNumOfBrowses;
}

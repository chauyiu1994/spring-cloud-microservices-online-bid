package com.chauyiu1994.onlineBidProductsService.models.product;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductListResponseModel {

    @NonNull
    private List<ProductResponseModel> productList;

    private String prevPageURL;
    private String nextPageURL;
}

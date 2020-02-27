package com.chauyiu1994.onlineBidProductsService.models.productDetail;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductDetailListResponseModel {

    @NonNull
    private List<ProductDetailResponseModel> productDetailList;

    private String prevURL;
    private String nextURL;
}

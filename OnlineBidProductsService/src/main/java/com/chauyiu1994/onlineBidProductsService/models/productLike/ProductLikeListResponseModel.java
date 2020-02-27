package com.chauyiu1994.onlineBidProductsService.models.productLike;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductLikeListResponseModel {

    @NonNull
    private List<ProductLikeResponseModel> productLikeList;

    private String prevURL;
    private String nextURL;
}

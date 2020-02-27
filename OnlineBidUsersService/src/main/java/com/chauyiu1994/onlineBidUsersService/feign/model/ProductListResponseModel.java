package com.chauyiu1994.onlineBidUsersService.feign.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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

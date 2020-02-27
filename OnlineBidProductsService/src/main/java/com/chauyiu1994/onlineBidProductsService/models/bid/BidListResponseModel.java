package com.chauyiu1994.onlineBidProductsService.models.bid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BidListResponseModel {

    @NonNull
    private List<BidResponseModel> bidResponseModels;

    private String prevURL;
    private String nextURL;
}

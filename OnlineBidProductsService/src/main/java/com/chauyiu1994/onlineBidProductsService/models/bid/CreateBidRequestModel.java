package com.chauyiu1994.onlineBidProductsService.models.bid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBidRequestModel {

    private String fromUserId;
    private int offer;
    private String message;
}

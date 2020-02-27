package com.chauyiu1994.onlineBidProductsService.models.bid;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidResponseModel {

    private String id;
    private Long productId;
    private String fromUserId;
    private Float offer;
    private String status;
    private String message;
    private LocalDateTime createTime;

    private String bidURL;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime getCreateTime() {
        return this.createTime;
    }
}

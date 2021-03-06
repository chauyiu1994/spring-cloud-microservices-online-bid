package com.chauyiu1994.onlineBidProductsService.models.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequestModel {

    private String fromUserId;
    private String content;
}

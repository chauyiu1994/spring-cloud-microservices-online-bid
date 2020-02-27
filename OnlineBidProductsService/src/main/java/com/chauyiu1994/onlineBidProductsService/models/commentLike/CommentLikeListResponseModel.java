package com.chauyiu1994.onlineBidProductsService.models.commentLike;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CommentLikeListResponseModel {

    @NonNull
    private List<CommentLikeResponseModel> commentLikeList;

    private String prevURL;
    private String nextURL;
}

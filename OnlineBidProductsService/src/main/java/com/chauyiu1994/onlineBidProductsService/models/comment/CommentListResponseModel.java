package com.chauyiu1994.onlineBidProductsService.models.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CommentListResponseModel {

    @NonNull
    private List<CommentResponseModel> comments;

    private String prevURL;
    private String nextURL;
}

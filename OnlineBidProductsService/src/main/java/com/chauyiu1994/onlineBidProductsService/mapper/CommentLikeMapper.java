package com.chauyiu1994.onlineBidProductsService.mapper;

import com.chauyiu1994.onlineBidProductsService.domains.CommentLike;
import com.chauyiu1994.onlineBidProductsService.models.commentLike.CreateCommentLikeRequestModel;
import com.chauyiu1994.onlineBidProductsService.models.commentLike.CommentLikeResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentLikeMapper {

    CommentLikeMapper INSTANCE = Mappers.getMapper(CommentLikeMapper.class);

    CommentLike createCommentLikeRequestModelToCommentLike(CreateCommentLikeRequestModel createCommentLikeRequestModel);

    CommentLikeResponseModel commentLikeToCommentLikeResponseModel(CommentLike commentLike);
}

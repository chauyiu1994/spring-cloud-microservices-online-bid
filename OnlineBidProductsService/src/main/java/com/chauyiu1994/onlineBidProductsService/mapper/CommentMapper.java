package com.chauyiu1994.onlineBidProductsService.mapper;

import com.chauyiu1994.onlineBidProductsService.domains.Comment;
import com.chauyiu1994.onlineBidProductsService.domains.CommentWithLike;
import com.chauyiu1994.onlineBidProductsService.models.comment.CreateCommentRequestModel;
import com.chauyiu1994.onlineBidProductsService.models.comment.CommentResponseModel;
import com.chauyiu1994.onlineBidProductsService.models.comment.UpdateCommentRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment createCommentRequestModelToComment(CreateCommentRequestModel createCommentRequestModel);

    Comment updateCommentRequestModelToComment(UpdateCommentRequestModel updateCommentRequestModel);

    CommentResponseModel commentToCommentResponseModel(Comment comment);

    CommentResponseModel commentWithLikeToCommentResponseModel(CommentWithLike commentWithLike);
}

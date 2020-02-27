package com.chauyiu1994.onlineBidProductsService.controllers;

import com.chauyiu1994.onlineBidProductsService.domains.Comment;
import com.chauyiu1994.onlineBidProductsService.mapper.CommentMapper;
import com.chauyiu1994.onlineBidProductsService.models.comment.CommentListResponseModel;
import com.chauyiu1994.onlineBidProductsService.models.comment.CommentResponseModel;
import com.chauyiu1994.onlineBidProductsService.models.comment.CreateCommentRequestModel;
import com.chauyiu1994.onlineBidProductsService.models.comment.UpdateCommentRequestModel;
import com.chauyiu1994.onlineBidProductsService.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@AllArgsConstructor
public class CommentController {

    CommentService commentService;
    CommentMapper commentMapper;

    @PostMapping("/products/{productId}/comments")
    public Mono<CommentResponseModel> create(@PathVariable Long productId, @RequestBody CreateCommentRequestModel requestModel) {

        Comment comment = commentMapper.createCommentRequestModelToComment(requestModel);
        comment.setProductId(productId);
        return commentService.addComment(comment)
                .map(commentMapper::commentToCommentResponseModel);
    }

    @GetMapping("/products/{productId}/comments/{commentId}")
    public Mono<CommentResponseModel> findComment(@PathVariable Long commentId) {

        return commentService.findComment(commentId)
                .map(commentMapper::commentToCommentResponseModel);
    }

    @GetMapping({"/comments", "/comments/search"})
    public Mono<CommentListResponseModel> searchComments(@RequestParam Map<String, String> paramMap) {

        if (paramMap.containsKey("cur-user-id")) {
            return commentService.findCommentsWithLike(paramMap)
                    .map(commentMapper::commentWithLikeToCommentResponseModel)
                    .collectList()
                    .map(CommentListResponseModel::new);
        }
        return commentService.findComments(paramMap)
                .map(commentMapper::commentToCommentResponseModel)
                .collectList()
                .map(CommentListResponseModel::new);
    }

    @GetMapping({"/products/{productId}/comments", "/products/{productId}/comments/search"})
    public Mono<CommentListResponseModel> searchCommentsWithProductId(@PathVariable Long productId, @RequestParam Map<String, String> paramMap) {

        paramMap.put("product-id", productId.toString());
        return searchComments(paramMap);
    }

    @PatchMapping("/products/{productId}/comments/{commentId}")
    public Mono<CommentResponseModel> updateComment(@PathVariable Long productId, @PathVariable Long commentId, @RequestBody UpdateCommentRequestModel requestModel) {

        return commentService.updateComment(productId, commentId, commentMapper.updateCommentRequestModelToComment(requestModel))
                .map(commentMapper::commentToCommentResponseModel);
    }

    @DeleteMapping("/products/{productId}/comments/{commentId}")
    public Mono<Void> deleteComment(@PathVariable Long productId, @PathVariable Long commentId) {

        return commentService.deleteComment(productId, commentId);
    }
}

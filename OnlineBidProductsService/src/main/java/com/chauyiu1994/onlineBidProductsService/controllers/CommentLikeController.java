package com.chauyiu1994.onlineBidProductsService.controllers;

import com.chauyiu1994.onlineBidProductsService.domains.CommentLike;
import com.chauyiu1994.onlineBidProductsService.mapper.CommentLikeMapper;
import com.chauyiu1994.onlineBidProductsService.models.commentLike.CommentLikeListResponseModel;
import com.chauyiu1994.onlineBidProductsService.models.commentLike.CommentLikeResponseModel;
import com.chauyiu1994.onlineBidProductsService.models.commentLike.CreateCommentLikeRequestModel;
import com.chauyiu1994.onlineBidProductsService.services.CommentLikeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@AllArgsConstructor
public class CommentLikeController {

    CommentLikeService commentLikeService;
    CommentLikeMapper commentLikeMapper;

    @PostMapping("/products/{productId}/comments/{commentId}/comment-likes")
    public Mono<CommentLikeResponseModel> create(@PathVariable Long commentId, @RequestBody CreateCommentLikeRequestModel requestModel) {

        CommentLike commentLike = commentLikeMapper.createCommentLikeRequestModelToCommentLike(requestModel);
        commentLike.setCommentId(commentId);
        return commentLikeService.addCommentLike(commentLike)
                .map(commentLikeMapper::commentLikeToCommentLikeResponseModel);
    }

    @GetMapping("/products/{productId}/comments/{commentId}/comment-likes/{commentLikeId}")
    public Mono<CommentLikeResponseModel> findCommentLike(@PathVariable Long commentLikeId) {

        return commentLikeService.findCommentLike(commentLikeId)
                .map(commentLikeMapper::commentLikeToCommentLikeResponseModel);
    }

    @GetMapping({"/comment-likes", "/comment-likes/search"})
    public Mono<CommentLikeListResponseModel> searchCommentLikes( @RequestParam Map<String, String> paramMap) {

        return commentLikeService.findCommentLikes(paramMap)
                .map(commentLikeMapper::commentLikeToCommentLikeResponseModel)
                .collectList()
                .map(CommentLikeListResponseModel::new);
    }

    @GetMapping({"/products/{productId}/comments/{commentId}/comment-likes", "/products/{productId}/comments/{commentId}/comment-likes/search"})
    public Mono<CommentLikeListResponseModel> searchCommentLikesWithCommentId(@PathVariable Long commentId, @RequestParam Map<String, String> paramMap) {

        paramMap.put("comment-id", commentId.toString());
        return searchCommentLikes(paramMap);
    }

    @DeleteMapping("/products/{productId}/comments/{commentId}/comment-likes/{commentLikeId}")
    public Mono<Void> deleteCommentLike(@PathVariable Long productId, @PathVariable Long commentId, @PathVariable Long commentLikeId) {

        return commentLikeService.deleteCommentLike(productId, commentId, commentLikeId);
    }
}

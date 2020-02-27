package com.chauyiu1994.onlineBidProductsService.services;

import com.chauyiu1994.onlineBidProductsService.domains.CommentLike;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface CommentLikeService {

    Mono<CommentLike> addCommentLike(CommentLike commentLike);
    Mono<CommentLike> findCommentLike(Long commentLikeId);
    Flux<CommentLike> findCommentLikes(Map<String, String> paramMap);
    Mono<Void> deleteCommentLike(Long productId, Long commentId, Long commentLikeId);
}

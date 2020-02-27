package com.chauyiu1994.onlineBidProductsService.services;

import com.chauyiu1994.onlineBidProductsService.domains.Comment;
import com.chauyiu1994.onlineBidProductsService.domains.CommentWithLike;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface CommentService {

    Mono<Comment> addComment(Comment comment);
    Mono<Comment> findComment(Long commentId);
    Flux<Comment> findComments(Map<String, String> paramMap);
    Flux<CommentWithLike> findCommentsWithLike(Map<String, String> paramMap);
    Mono<Comment> updateComment(Long productId, Long commentId, Comment comment);
    Mono<Void> deleteComment(Long productId, Long commentId);
}

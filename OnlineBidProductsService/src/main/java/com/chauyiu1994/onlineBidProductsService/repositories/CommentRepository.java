package com.chauyiu1994.onlineBidProductsService.repositories;

import com.chauyiu1994.onlineBidProductsService.domains.Comment;
import com.chauyiu1994.onlineBidProductsService.domains.CommentWithLike;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLJoinBuildEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentRepository {

    Mono<Comment> save(Comment bid);
    Mono<Comment> findById(Long id);
    Mono<Void> deleteById(Long id);
    Flux<Comment> findMany(SQLBuildEntity entity);
    Flux<CommentWithLike> findCommentWithUserLikes(SQLJoinBuildEntity entity);
}

package com.chauyiu1994.onlineBidProductsService.repositories;

import com.chauyiu1994.onlineBidProductsService.domains.CommentLike;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentLikeRepository {

    Mono<CommentLike> save(CommentLike bid);
    Mono<CommentLike> findById(Long id);
    Mono<Void> deleteById(Long id);
    Flux<CommentLike> findMany(SQLBuildEntity entity);
}

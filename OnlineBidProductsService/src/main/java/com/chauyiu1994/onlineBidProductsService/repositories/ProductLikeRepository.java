package com.chauyiu1994.onlineBidProductsService.repositories;

import com.chauyiu1994.onlineBidProductsService.domains.ProductLike;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductLikeRepository {

    Mono<ProductLike> save(ProductLike bid);
    Mono<ProductLike> findById(Long id);
    Mono<Void> deleteById(Long id);
    Flux<ProductLike> findMany(SQLBuildEntity entity);
}

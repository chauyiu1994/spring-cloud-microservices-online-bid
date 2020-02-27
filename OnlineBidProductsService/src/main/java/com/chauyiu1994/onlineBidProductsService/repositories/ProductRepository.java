package com.chauyiu1994.onlineBidProductsService.repositories;

import com.chauyiu1994.onlineBidProductsService.domains.Product;
import com.chauyiu1994.onlineBidProductsService.domains.ProductWithLike;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLJoinBuildEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {

    Mono<Product> save(Product bid);
    Mono<Product> findById(Long id);
    Mono<Void> deleteById(Long id);
    Flux<Product> findMany(SQLBuildEntity entity);
    Flux<ProductWithLike> findProductWithUserLikes(SQLJoinBuildEntity entity);
}

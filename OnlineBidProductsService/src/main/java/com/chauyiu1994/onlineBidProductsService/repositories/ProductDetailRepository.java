package com.chauyiu1994.onlineBidProductsService.repositories;

import com.chauyiu1994.onlineBidProductsService.domains.ProductDetail;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductDetailRepository {

    Mono<ProductDetail> save(ProductDetail bid);
    Mono<ProductDetail> findById(Long id);
    Mono<Void> deleteById(Long id);
    Flux<ProductDetail> findMany(SQLBuildEntity entity);
}

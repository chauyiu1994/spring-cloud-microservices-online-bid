package com.chauyiu1994.onlineBidProductsService.services;

import com.chauyiu1994.onlineBidProductsService.domains.ProductLike;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface ProductLikeService {

    Mono<ProductLike> addProductLike(ProductLike productLike);
    Mono<ProductLike> findProductLike(Long productLikeId);
    Flux<ProductLike> findProductLikes(Map<String, String> paramMap);
    Mono<Void> deleteProductLike(Long productId, Long productLikeId);
}

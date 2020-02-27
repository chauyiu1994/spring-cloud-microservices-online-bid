package com.chauyiu1994.onlineBidProductsService.services;

import com.chauyiu1994.onlineBidProductsService.domains.Product;
import com.chauyiu1994.onlineBidProductsService.domains.ProductWithLike;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface ProductService {

    Mono<Product> addProduct(Product product);
    Mono<Product> findProduct(Long productId);
    Flux<Product> findProducts(Map<String, String> paramMap);
    Flux<ProductWithLike> findProductsWithLike(Map<String, String> paramMap);
    Mono<Product> updateProduct(Long productId, Product product, boolean isIncrNumOfBrowses);
    Mono<Void> deleteProduct(Long productId);
}

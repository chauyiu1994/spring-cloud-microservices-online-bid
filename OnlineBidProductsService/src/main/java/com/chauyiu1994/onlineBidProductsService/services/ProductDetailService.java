package com.chauyiu1994.onlineBidProductsService.services;

import com.chauyiu1994.onlineBidProductsService.domains.ProductDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface ProductDetailService {

    Mono<ProductDetail> addProductDetail(ProductDetail productDetail);
    Mono<ProductDetail> findProductDetail(Long productDetailId);
    Flux<ProductDetail> findProductDetails(Map<String, String> paramMap);
    Mono<ProductDetail> updateProductDetail(Long productId, Long productDetailId, ProductDetail productDetail);
    Mono<Void> deleteProductDetail(Long productId, Long productDetailId);
}

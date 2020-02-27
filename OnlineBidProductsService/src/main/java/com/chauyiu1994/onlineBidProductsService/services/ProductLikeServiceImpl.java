package com.chauyiu1994.onlineBidProductsService.services;

import com.chauyiu1994.onlineBidProductsService.domains.ProductLike;
import com.chauyiu1994.onlineBidProductsService.repositories.ProductLikeRepository;
import com.chauyiu1994.onlineBidProductsService.services.searchUtils.ProductLikeSearchUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@AllArgsConstructor
public class ProductLikeServiceImpl implements ProductLikeService {

    private ProductLikeRepository productLikeRepository;
    private ProductLikeSearchUtil productLikeSearchUtil;

    @Override
    public Mono<ProductLike> addProductLike(ProductLike productLike) {

        return productLikeRepository.save(productLike);
    }

    @Override
    public Mono<ProductLike> findProductLike(Long productLikeId) {

        return productLikeRepository.findById(productLikeId);
    }

    @Override
    public Flux<ProductLike> findProductLikes(Map<String, String> paramMap) {

        return productLikeRepository.findMany(productLikeSearchUtil.getEntity(paramMap));
    }

    @Override
    public Mono<Void> deleteProductLike(Long productId, Long productLikeId) {

        return findProductLike(productLikeId).flatMap(targetProductLike -> {
            if (targetProductLike == null || targetProductLike.getProductId() != productId) return Mono.empty();
            return productLikeRepository.deleteById(productLikeId);
        });
    }
}

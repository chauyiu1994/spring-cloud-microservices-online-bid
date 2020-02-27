package com.chauyiu1994.onlineBidProductsService.services;

import com.chauyiu1994.onlineBidProductsService.domains.ProductDetail;
import com.chauyiu1994.onlineBidProductsService.repositories.ProductDetailRepository;
import com.chauyiu1994.onlineBidProductsService.services.patchUtils.ProductDetailPatchUtil;
import com.chauyiu1994.onlineBidProductsService.services.searchUtils.ProductDetailSearchUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@AllArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {

    private ProductDetailRepository productDetailRepository;
    private ProductDetailSearchUtil productDetailSearchUtil;
    private ProductDetailPatchUtil productDetailPatchUtil;

    @Override
    public Mono<ProductDetail> addProductDetail(ProductDetail productDetail) {

        return productDetailRepository.save(productDetail);
    }

    @Override
    public Mono<ProductDetail> findProductDetail(Long productDetailId) {

        return productDetailRepository.findById(productDetailId);
    }

    @Override
    public Flux<ProductDetail> findProductDetails(Map<String, String> paramMap) {

        return productDetailRepository.findMany(productDetailSearchUtil.getEntity(paramMap));
    }

    @Override
    public Mono<ProductDetail> updateProductDetail(Long productId, Long productDetailId, ProductDetail productDetail) {

        return findProductDetail(productDetailId).flatMap(targetProductDetail -> {
            if (targetProductDetail == null || targetProductDetail.getProductId() != productId) return Mono.empty();
            return productDetailPatchUtil.patchProductDetail(targetProductDetail, productDetail)
                    ? productDetailRepository.save(targetProductDetail)
                    : Mono.empty();
        });
    }

    @Override
    public Mono<Void> deleteProductDetail(Long productId, Long productDetailId) {

        return findProductDetail(productDetailId).flatMap(targetProductDetail -> {
            if (targetProductDetail == null || targetProductDetail.getProductId() != productId) return Mono.empty();
            return productDetailRepository.deleteById(productDetailId);
        });
    }
}

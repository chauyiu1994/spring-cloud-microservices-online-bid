package com.chauyiu1994.onlineBidProductsService.services;

import com.chauyiu1994.onlineBidProductsService.domains.Product;
import com.chauyiu1994.onlineBidProductsService.domains.ProductWithLike;
import com.chauyiu1994.onlineBidProductsService.repositories.ProductRepository;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLJoinBuildEntity;
import com.chauyiu1994.onlineBidProductsService.services.searchUtils.ProductSearchUtil;
import com.chauyiu1994.onlineBidProductsService.services.patchUtils.ProductPatchUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@AllArgsConstructor
public class ProductsServiceImpl implements ProductService {

    private ProductPatchUtil productPatchUtil;
    private ProductSearchUtil productSearchUtil;

    private ProductRepository productRepository;

    @Override
    public Mono<Product> findProduct(Long productId) {

        return productRepository.findById(productId);
    }

    @Override
    public Mono<Product> addProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public Flux<Product> findProducts(Map<String, String> paramMap) {

        return productRepository.findMany(productSearchUtil.getEntity(paramMap));
    }

    @Override
    public Flux<ProductWithLike> findProductsWithLike(Map<String, String> paramMap) {

        return productRepository.findProductWithUserLikes((SQLJoinBuildEntity) productSearchUtil.getEntity(paramMap));
    }

    @Override
    public Mono<Product> updateProduct(Long productId, Product product, boolean isIncrNumOfBrowses) {

        return findProduct(productId).flatMap(targetProduct -> {
            if (targetProduct == null) return Mono.empty();
            if (productPatchUtil.patchProduct(targetProduct, product, isIncrNumOfBrowses)) {
                return addProduct(targetProduct);
            }
            return Mono.just(targetProduct);
        });
    }

    @Override
    public Mono<Void> deleteProduct(Long productId) {

        return productRepository.deleteById(productId);
    }

}

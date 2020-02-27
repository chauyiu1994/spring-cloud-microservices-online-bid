package com.chauyiu1994.onlineBidProductsService.controllers;

import com.chauyiu1994.onlineBidProductsService.domains.ProductLike;
import com.chauyiu1994.onlineBidProductsService.mapper.ProductLikeMapper;
import com.chauyiu1994.onlineBidProductsService.models.productLike.CreateProductLikeRequestModel;
import com.chauyiu1994.onlineBidProductsService.models.productLike.ProductLikeListResponseModel;
import com.chauyiu1994.onlineBidProductsService.models.productLike.ProductLikeResponseModel;
import com.chauyiu1994.onlineBidProductsService.services.ProductLikeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@AllArgsConstructor
public class ProductLikeController {

    ProductLikeService productLikeService;
    ProductLikeMapper productLikeMapper;

    @PostMapping("/products/{productId}/product-likes")
    public Mono<ProductLikeResponseModel> create(@PathVariable Long productId, @RequestBody CreateProductLikeRequestModel requestModel) {

        ProductLike productLike = productLikeMapper.createProductLikeRequestModelToProductLike(requestModel);
        productLike.setProductId(productId);
        return productLikeService.addProductLike(productLike)
                .map(productLikeMapper::productLikeToProductLikeResponseModel);
    }

    @GetMapping("/products/{productId}/product-likes/{productLikeId}")
    public Mono<ProductLikeResponseModel> findProductLike(@PathVariable Long productLikeId) {

        return productLikeService.findProductLike(productLikeId)
                .map(productLikeMapper::productLikeToProductLikeResponseModel);
    }

    @GetMapping({"/product-like", "/product-likes/search"})
    public Mono<ProductLikeListResponseModel> searchProductLikes(@RequestParam Map<String, String> paramMap) {

        return productLikeService.findProductLikes(paramMap)
                .map(productLikeMapper::productLikeToProductLikeResponseModel)
                .collectList()
                .map(ProductLikeListResponseModel::new);
    }

    @GetMapping({"/products/{productId}/product-likes", "/products/{productId}/product-like/search"})
    public Mono<ProductLikeListResponseModel> searchProductLikesWithProductId(@PathVariable Long productId, @RequestParam Map<String, String> paramMap) {

        paramMap.put("product-id", productId.toString());
        return searchProductLikes(paramMap);
    }

    @DeleteMapping("/products/{productId}/product-likes/{productLikeId}")
    public Mono<Void> deleteProductLike(@PathVariable Long productId, @PathVariable Long productLikeId) {

        return productLikeService.deleteProductLike(productId, productLikeId);
    }
}

package com.chauyiu1994.onlineBidProductsService.controllers;

import com.chauyiu1994.onlineBidProductsService.mapper.ProductMapper;
import com.chauyiu1994.onlineBidProductsService.models.product.CreateProductRequestModel;
import com.chauyiu1994.onlineBidProductsService.models.product.ProductListResponseModel;
import com.chauyiu1994.onlineBidProductsService.models.product.ProductResponseModel;
import com.chauyiu1994.onlineBidProductsService.models.product.UpdateProductRequestModel;
import com.chauyiu1994.onlineBidProductsService.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    private ProductMapper productMapper;

    @PostMapping("/products")
    public Mono<ProductResponseModel> create(@RequestBody CreateProductRequestModel createProductRequestModel) {

        return productService.addProduct(productMapper.createProductRequestModelToProduct(createProductRequestModel))
            .map(productMapper::productToProductResponseModel);
    }

    @GetMapping("/products/{productId}")
    public Mono<ProductResponseModel> findProduct(@PathVariable Long productId) {

        return productService.findProduct(productId).map(productMapper::productToProductResponseModel);
    }

    @GetMapping({"/products", "/products/search"})
    public Mono<ProductListResponseModel> searchProducts(@RequestParam Map<String, String> paramMap) {

        if (paramMap.containsKey("cur-user-id")) {
            return productService.findProductsWithLike(paramMap)
                    .map(productMapper::productWithLikeToProductResponseModel)
                    .collectList()
                    .map(ProductListResponseModel::new);
        }
        return productService.findProducts(paramMap)
                .map(productMapper::productToProductResponseModel)
                .collectList()
                .map(ProductListResponseModel::new);
    }

    @PatchMapping("/products/{productId}")
    public Mono<ProductResponseModel> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductRequestModel model) {

        return productService
                .updateProduct(productId, productMapper.updateProductRequestModelToProduct(model), model.isIncrNumOfBrowses())
                .map(productMapper::productToProductResponseModel);
    }

    @DeleteMapping("/products/{productId}")
    public Mono<Void> deleteProduct(@PathVariable Long productId) {

        return productService.deleteProduct(productId);
    }
}

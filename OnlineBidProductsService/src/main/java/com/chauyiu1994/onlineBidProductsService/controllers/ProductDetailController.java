package com.chauyiu1994.onlineBidProductsService.controllers;

import com.chauyiu1994.onlineBidProductsService.domains.ProductDetail;
import com.chauyiu1994.onlineBidProductsService.mapper.ProductDetailMapper;
import com.chauyiu1994.onlineBidProductsService.models.productDetail.CreateProductDetailRequestModel;
import com.chauyiu1994.onlineBidProductsService.models.productDetail.ProductDetailListResponseModel;
import com.chauyiu1994.onlineBidProductsService.models.productDetail.ProductDetailResponseModel;
import com.chauyiu1994.onlineBidProductsService.models.productDetail.UpdateProductDetailRequestModel;
import com.chauyiu1994.onlineBidProductsService.services.ProductDetailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@AllArgsConstructor
public class ProductDetailController {

    ProductDetailService productDetailService;
    ProductDetailMapper productDetailMapper;

    @PostMapping("/products/{productId}/product-details")
    public Mono<ProductDetailResponseModel> create(@PathVariable Long productId, @RequestBody CreateProductDetailRequestModel requestModel) {

        ProductDetail productDetail = productDetailMapper.createProductDetailRequestModelToProductDetail(requestModel);
        productDetail.setProductId(productId);
        return productDetailService.addProductDetail(productDetail)
                .map(productDetailMapper::productDetailToProductDetailResponseModel);
    }

    @GetMapping("/products/{productId}/product-details/{productDetailId}")
    public Mono<ProductDetailResponseModel> findProductDetail(@PathVariable Long productDetailId) {

        return productDetailService.findProductDetail(productDetailId)
                .map(productDetailMapper::productDetailToProductDetailResponseModel);
    }


    @GetMapping({"/products/{productId}/product-details", "/products/{productId}/product-details/search"})
    public Mono<ProductDetailListResponseModel> searchProductDetails(@PathVariable Long productId, @RequestParam Map<String, String> paramMap) {

        paramMap.put("product-id", productId.toString());
        return productDetailService.findProductDetails(paramMap)
                .map(productDetailMapper::productDetailToProductDetailResponseModel)
                .collectList()
                .map(ProductDetailListResponseModel::new);
    }

    @PatchMapping("/products/{productId}/product-details/{productDetailId}")
    public Mono<ProductDetailResponseModel> updateProductDetail(@PathVariable Long productId, @PathVariable Long productDetailId, @RequestBody UpdateProductDetailRequestModel requestModel) {

        return productDetailService.updateProductDetail(productId, productDetailId, productDetailMapper.updateProductDetailRequestModelToProductDetail(requestModel))
                .map(productDetailMapper::productDetailToProductDetailResponseModel);
    }

    @DeleteMapping("/products/{productId}/product-details/{productDetailId}")
    public Mono<Void> deleteProductDetail(@PathVariable Long productId, @PathVariable Long productDetailId) {

        return productDetailService.deleteProductDetail(productId, productDetailId);
    }
}

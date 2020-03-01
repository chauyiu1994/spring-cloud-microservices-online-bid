package com.chauyiu1994.onlineBidUsersService.feign;

import com.chauyiu1994.onlineBidUsersService.feign.model.ProductListResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "product-ws")
public interface ProductClient {

    @GetMapping("/products")
    Mono<ProductListResponseModel> getProducts();
}

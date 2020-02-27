package com.chauyiu1994.onlineBidProductsService.repositories.baseRepositories;

import com.chauyiu1994.onlineBidProductsService.domains.ProductDetail;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductDetailBaseRepository extends ReactiveCrudRepository<ProductDetail, Long> {
}

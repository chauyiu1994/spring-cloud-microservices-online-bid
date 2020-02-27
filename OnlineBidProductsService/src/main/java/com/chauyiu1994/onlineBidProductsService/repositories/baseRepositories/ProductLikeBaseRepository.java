package com.chauyiu1994.onlineBidProductsService.repositories.baseRepositories;

import com.chauyiu1994.onlineBidProductsService.domains.ProductLike;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductLikeBaseRepository extends ReactiveCrudRepository<ProductLike, Long> {
}

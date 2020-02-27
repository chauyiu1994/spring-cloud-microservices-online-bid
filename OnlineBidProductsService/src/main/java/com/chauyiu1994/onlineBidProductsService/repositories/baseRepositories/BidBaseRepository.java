package com.chauyiu1994.onlineBidProductsService.repositories.baseRepositories;

import com.chauyiu1994.onlineBidProductsService.domains.Bid;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BidBaseRepository extends ReactiveCrudRepository<Bid, Long> {
}

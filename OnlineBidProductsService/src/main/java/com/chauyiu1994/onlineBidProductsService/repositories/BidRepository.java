package com.chauyiu1994.onlineBidProductsService.repositories;

import com.chauyiu1994.onlineBidProductsService.domains.Bid;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BidRepository {

    Mono<Bid> save(Bid bid);
    Mono<Bid> findById(Long id);
    Flux<Bid> findMany(SQLBuildEntity entity);
    Mono<Void> deleteById(Long id);
}

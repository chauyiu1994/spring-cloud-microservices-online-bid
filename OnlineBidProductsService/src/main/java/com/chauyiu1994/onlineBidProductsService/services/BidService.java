package com.chauyiu1994.onlineBidProductsService.services;

import com.chauyiu1994.onlineBidProductsService.domains.Bid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface BidService {

    Mono<Bid> addBid(Bid bid);
    Mono<Bid> findBid(Long bidId);
    Flux<Bid> findBids(Map<String, String> paramMap);
    Mono<Bid> updateBid(Long productId, Long bidId, Bid bid);
    Mono<Void> deleteBid(Long productId, Long bidId);
}

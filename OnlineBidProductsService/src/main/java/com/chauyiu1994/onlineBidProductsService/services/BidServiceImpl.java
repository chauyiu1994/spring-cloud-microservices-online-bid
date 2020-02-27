package com.chauyiu1994.onlineBidProductsService.services;

import com.chauyiu1994.onlineBidProductsService.domains.Bid;
import com.chauyiu1994.onlineBidProductsService.repositories.BidRepository;
import com.chauyiu1994.onlineBidProductsService.services.patchUtils.BidPatchUtil;
import com.chauyiu1994.onlineBidProductsService.services.searchUtils.BidSearchUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@AllArgsConstructor
public class BidServiceImpl implements BidService {

    private BidRepository bidRepository;
    private BidSearchUtil bidSearchUtil;
    private BidPatchUtil bidPatchUtil;


    @Override
    public Mono<Bid> addBid(Bid bid) {

        return bidRepository.save(bid);
    }

    @Override
    public Mono<Bid> findBid(Long bidId) {

        return bidRepository.findById(bidId);
    }

    @Override
    public Flux<Bid> findBids(Map<String, String> paramMap) {

        return bidRepository.findMany(bidSearchUtil.getEntity(paramMap));
    }

    @Override
    public Mono<Bid> updateBid(Long productId, Long bidId, Bid bid) {

        if (bid.getStatus() == null) return Mono.empty();
        return findBid(bidId).flatMap(targetBid -> {
            if (targetBid == null || targetBid.getId() != productId) return Mono.empty();
            return bidPatchUtil.patchBid(targetBid, bid)
                    ? bidRepository.save(targetBid)
                    : Mono.empty();
        });
    }

    @Override
    public Mono<Void> deleteBid(Long productId, Long bidId) {

        return findBid(bidId).flatMap(targetBid -> {
            if (targetBid == null || targetBid.getId() != productId) return Mono.empty();
            return bidRepository.deleteById(bidId);
        });
    }
}

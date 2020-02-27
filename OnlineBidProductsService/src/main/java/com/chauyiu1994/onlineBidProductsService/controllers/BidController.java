package com.chauyiu1994.onlineBidProductsService.controllers;

import com.chauyiu1994.onlineBidProductsService.domains.Bid;
import com.chauyiu1994.onlineBidProductsService.mapper.BidMapper;
import com.chauyiu1994.onlineBidProductsService.models.bid.BidListResponseModel;
import com.chauyiu1994.onlineBidProductsService.models.bid.CreateBidRequestModel;
import com.chauyiu1994.onlineBidProductsService.models.bid.UpdateBidRequestModel;
import com.chauyiu1994.onlineBidProductsService.models.bid.BidResponseModel;
import com.chauyiu1994.onlineBidProductsService.services.BidService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class BidController {

    BidService bidService;
    BidMapper bidMapper;

    @PostMapping("/products/{productId}/bids")
    public Mono<BidResponseModel> create(@PathVariable Long productId, @RequestBody CreateBidRequestModel requestModel) {

        Bid bid = bidMapper.createBidRequestModelToBid(requestModel);
        bid.setProductId(productId);
        return bidService.addBid(bid)
                .map(bidMapper::bidToBidResponseModel);
    }

    @GetMapping("/bids/{bidId}")
    public Mono<BidResponseModel> findBid(@PathVariable Long bidId) {

        return bidService.findBid(bidId)
                .map(bidMapper::bidToBidResponseModel);
    }

    @GetMapping("/products/{productId}/bids/{bidId}")
    public Mono<BidResponseModel> findBidWithProductId(@PathVariable Long bidId, @PathVariable Long productId) {

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("id", bidId.toString());
        paramMap.put("product-id", productId.toString());
        return bidService.findBids(paramMap)
                .next()
                .map(bidMapper::bidToBidResponseModel);
    }

    @GetMapping({"/bids", "/bids/search"})
    public Mono<BidListResponseModel> searchBids(@RequestParam Map<String, String> paramMap) {

        return bidService.findBids(paramMap)
                .map(bidMapper::bidToBidResponseModel)
                .collectList()
                .map(BidListResponseModel::new);
    }

    @GetMapping({"/products/{productId}/bids", "/products/{productId}/bids/search"})
    public Mono<BidListResponseModel> searchBidsWithProductId(@PathVariable Long productId, @RequestParam Map<String, String> paramMap) {

        paramMap.put("product-id", productId.toString());
        return searchBids(paramMap);
    }

    // has to match both productId and bidId for deleting and updating
    @PatchMapping("/products/{productId}/bids/{bidId}")
    public Mono<BidResponseModel> updateBid(@PathVariable Long productId, @PathVariable Long bidId, @RequestBody UpdateBidRequestModel requestModel) {

        return bidService.updateBid(productId, bidId, bidMapper.updateBidRequestModelToBid(requestModel))
                .map(bidMapper::bidToBidResponseModel);
    }

    @DeleteMapping("/products/{productId}/bids/{bidId}")
    public Mono<Void> deleteBid(@PathVariable Long productId, @PathVariable Long bidId) {

        return bidService.deleteBid(productId, bidId);
    }
}

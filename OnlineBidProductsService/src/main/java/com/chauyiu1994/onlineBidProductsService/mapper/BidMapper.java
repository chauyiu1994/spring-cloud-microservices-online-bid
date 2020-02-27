package com.chauyiu1994.onlineBidProductsService.mapper;

import com.chauyiu1994.onlineBidProductsService.domains.Bid;
import com.chauyiu1994.onlineBidProductsService.models.bid.CreateBidRequestModel;
import com.chauyiu1994.onlineBidProductsService.models.bid.UpdateBidRequestModel;
import com.chauyiu1994.onlineBidProductsService.models.bid.BidResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BidMapper {

    BidMapper INSTANCE = Mappers.getMapper(BidMapper.class);

    Bid createBidRequestModelToBid(CreateBidRequestModel createBidRequestModel);

    Bid updateBidRequestModelToBid(UpdateBidRequestModel updateBidRequestModel);

    BidResponseModel bidToBidResponseModel(Bid bid);
}

package com.chauyiu1994.onlineBidProductsService.services.patchUtils;

import com.chauyiu1994.onlineBidProductsService.domains.Bid;
import org.springframework.stereotype.Component;

// patch only allow on status
@Component
public class BidPatchUtil {

    public boolean patchBid(Bid targetBid, Bid bid) {

        return updateStatus(targetBid, bid);
    }

    private boolean updateStatus(Bid targetBid, Bid bid) {

        if (bid.getStatus() != null && !bid.getStatus().equals(targetBid.getStatus())) {
            targetBid.setStatus(bid.getStatus());
            return true;
        }
        return false;
    }
}

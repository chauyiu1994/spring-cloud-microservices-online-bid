package com.chauyiu1994.onlineBidProductsService.services.searchUtils;

import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import com.chauyiu1994.onlineBidProductsService.utils.PageableUtil;
import com.chauyiu1994.onlineBidProductsService.utils.ParamMapParseUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

// FIND BY:
// 1. id
// 2. status
// 3. product-id
// 4. from-user-id

// SORT BY:
// 1. status
// 2. createTime
// 3. offer

@Component
@AllArgsConstructor
public class BidSearchUtil {

    private PageableUtil pageableUtil;
    private ParamMapParseUtil paramMapParseUtil;

    public SQLBuildEntity getEntity(Map<String, String> paramMap) {

        Pageable pageable = pageableUtil.getPageable(paramMap, "OFFER", "desc", Arrays.asList("STATUS", "CREATE_TIME", "OFFER"));

        Long id = paramMapParseUtil.parseLongValue(paramMap, "id");
        String status = paramMapParseUtil.parseStringValue(paramMap, "status");
        Long productId = paramMapParseUtil.parseLongValue(paramMap, "product-id");
        Long fromUserId = paramMapParseUtil.parseLongValue(paramMap, "from-user-id");

        SQLBuildEntity entity = new SQLBuildEntity();
        entity.setTableName("BID");
        if (id != null) entity.addEqual("ID", id);
        if (status != null) entity.addEqual("STATUS", status);
        if (productId != null) entity.addEqual("PRODUCT_ID", productId);
        if (fromUserId != null) entity.addEqual("FROM_USER_ID", fromUserId);
        entity.setPageable(pageable);

        return entity;
    }
}

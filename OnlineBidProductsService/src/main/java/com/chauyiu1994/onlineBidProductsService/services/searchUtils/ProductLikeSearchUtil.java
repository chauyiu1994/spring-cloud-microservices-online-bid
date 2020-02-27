package com.chauyiu1994.onlineBidProductsService.services.searchUtils;

import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import com.chauyiu1994.onlineBidProductsService.utils.PageableUtil;
import com.chauyiu1994.onlineBidProductsService.utils.ParamMapParseUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

// FIND BY
// 1. id
// 2. productId
// 3. fromUserId

// SORT BY
// 1. createTime

@Component
@AllArgsConstructor
public class ProductLikeSearchUtil {

    private PageableUtil pageableUtil;
    private ParamMapParseUtil paramMapParseUtil;

    public SQLBuildEntity getEntity(Map<String, String> paramMap) {

        Pageable pageable = pageableUtil.getPageable(paramMap, "CREATE_TIME", "desc", Arrays.asList("CREATE_TIME"));

        Long id = paramMapParseUtil.parseLongValue(paramMap, "id");
        Long productId = paramMapParseUtil.parseLongValue(paramMap, "product-id");
        String fromUserId = paramMapParseUtil.parseStringValue(paramMap, "from-user-id");

        SQLBuildEntity entity = new SQLBuildEntity();
        entity.setTableName("PRODUCT_LIKE");
        if (id != null) entity.addEqual("ID", id);
        if (productId != null) entity.addEqual("PRODUCT_ID", productId);
        if (fromUserId != null) entity.addEqual("FROM_USER_ID", fromUserId);
        entity.setPageable(pageable);

        return entity;
    }
}

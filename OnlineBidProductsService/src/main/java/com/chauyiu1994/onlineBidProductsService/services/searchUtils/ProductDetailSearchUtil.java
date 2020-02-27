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
// 1: id
// 2. productId
// 3. position

// SORT BY:
// 1. position
// 2. lastModifiedTime

@Component
@AllArgsConstructor
public class ProductDetailSearchUtil {

    private PageableUtil pageableUtil;
    private ParamMapParseUtil paramMapParseUtil;

    public SQLBuildEntity getEntity(Map<String, String> paramMap) {

        Pageable pageable = pageableUtil.getPageable(paramMap, "POSITION", "asc", Arrays.asList("POSITION", "LAST_MODIFIED_TIME"));

        Long id = paramMapParseUtil.parseLongValue(paramMap, "id");
        Long productId = paramMapParseUtil.parseLongValue(paramMap, "product-id");
        Integer position = paramMapParseUtil.parseIntValue(paramMap, "position");

        SQLBuildEntity entity = new SQLBuildEntity();
        entity.setTableName("PRODUCT_DETAIL");
        if (id != null) entity.addEqual("ID", id);
        if (productId != null) entity.addEqual("PRODUCT_ID", productId);
        if (position != null) entity.addEqual("POSITION", position);
        entity.setPageable(pageable);

        return entity;
    }
}

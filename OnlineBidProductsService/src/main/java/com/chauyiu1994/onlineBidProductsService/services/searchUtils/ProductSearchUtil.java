package com.chauyiu1994.onlineBidProductsService.services.searchUtils;

import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLJoinBuildEntity;
import com.chauyiu1994.onlineBidProductsService.utils.ParamMapParseUtil;
import com.chauyiu1994.onlineBidProductsService.utils.PageableUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

// FIND BY:
// 1. sellerUserId
// 2. buyerUserId
// 3. name (regex) + category + price range

// SORT BY
// 1. price
// 2. numOfLikes
// 3. numOfBids
// 4. numOfComments
// 5. numOfBrowses
// 6. createTime
// 7. lastModifiedTime

@Component
@AllArgsConstructor
public class ProductSearchUtil {

    private PageableUtil pageableUtil;
    private ParamMapParseUtil paramMapParseUtil;

    public SQLBuildEntity getEntity(Map<String, String> paramMap) {

        Pageable pageable = pageableUtil.getPageable(paramMap, "CREATE_TIME", "desc",
                Arrays.asList("PRICE", "NUM_OF_LIKE", "NUM_OF_BID", "NUM_OF_COMMENT", "NOM_OF_BROWSE", "CREATE_TIME", "LAST_MODIFIED_TIME"));

        String sellerUserId = paramMapParseUtil.parseStringValue(paramMap,"seller-user-id");
        String buyerUserId = paramMapParseUtil.parseStringValue(paramMap,"buyer-user-id");
        String[] keywords = paramMapParseUtil.parseArrayValue(paramMap, "q");
        boolean isStrictSearch = paramMapParseUtil.parseBooleanValue(paramMap, "strict");
        String category = paramMapParseUtil.parseStringValue(paramMap,"category");
        Float priceFrom = paramMapParseUtil.parseFloatValue(paramMap, "price-from");
        Float priceTo = paramMapParseUtil.parseFloatValue(paramMap, "price-to");
        String curUserId = paramMapParseUtil.parseStringValue(paramMap, "cur-user-id");
        String regex = null;

        if (keywords != null) {
            if (isStrictSearch) {
                regex = "";
                for (String keyword : keywords) {
                    regex += "(?=.*?" + keyword + ")";
                }
            } else {
                regex = String.join("|", keywords);
            }
        }

        return findByCategoryAndPriceRangeAndNameKeyWords(sellerUserId, buyerUserId, category, priceFrom, priceTo, regex, pageable, curUserId);
    }

    private SQLBuildEntity findByCategoryAndPriceRangeAndNameKeyWords(String sellerUserId, String buyerUserId, String category, Float priceFrom,
                                                                      Float priceTo, String regex, Pageable pageable, String curUserId) {

        SQLBuildEntity entity;
        if (curUserId != null) {
            SQLJoinBuildEntity joinEntity = new SQLJoinBuildEntity();
            joinEntity.setLefterOuterJoin(true);
            joinEntity.setToTableName("PRODUCT_LIKE");
            joinEntity.addChildEqual("FROM_USER_ID", curUserId);
            joinEntity.addRequiredFields("ID", "PRODUCT_LIKE_ID");
            joinEntity.addJoinKey("ID", "PRODUCT_ID");
            entity = joinEntity;
        } else {
            entity = new SQLBuildEntity();
        }
        entity.setTableName("PRODUCT");
        if (sellerUserId != null && !sellerUserId.trim().equals("")) entity.addEqual("SELLER_USER_ID", sellerUserId);
        if (buyerUserId != null && !buyerUserId.trim().equals("")) entity.addEqual("BUYER_USER_ID", buyerUserId);
        if (category != null && !category.trim().equals("")) entity.addEqual("CATEGORY", category);
        if (priceFrom != null) entity.addGT("PRICE", priceFrom);
        if (priceTo != null) entity.addLT("PRICE", priceTo);
        if (regex != null && !regex.trim().equals("")) entity.addRegex("NAME", regex);
        entity.setPageable(pageable);
        return entity;
    }
}

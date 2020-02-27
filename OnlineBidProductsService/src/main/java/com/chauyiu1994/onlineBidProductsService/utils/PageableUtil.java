package com.chauyiu1994.onlineBidProductsService.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PageableUtil {

    public Pageable getPageable(Map<String, String> paramMap, String defaultSort, String defaultOrder, List<String> acceptedSort) {

        Sort sort = Sort.by(defaultSort);
        String order = defaultOrder;
        int page = 0;
        int size = 30;

        if (paramMap.containsKey("sort") && !paramMap.get("sort").trim().equals("")) {

            String sortStr = paramMap.get("sort").trim().replace("-", "_").toUpperCase();

            // prevent sql injection
            if (acceptedSort.contains(sortStr)) {
                sort = Sort.by(sortStr);
            }
        }

        if (paramMap.containsKey("order") && paramMap.get("order").trim().toLowerCase().equals("desc")) {
            sort = sort.descending();
        } else if (paramMap.containsKey("order") && paramMap.get("order").trim().toLowerCase().equals("asc")) {
            sort = sort.ascending();
        } else if (defaultOrder != null && defaultOrder == "desc") {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        if (paramMap.containsKey("page") && !paramMap.get("page").trim().equals("")) {
            try {
                page = Integer.parseInt(paramMap.get("page"));
            } catch (NumberFormatException e) {}
        }

        if (paramMap.containsKey("size") && !paramMap.get("size").trim().equals("")) {
            try {
                int parsedSize = Integer.parseInt(paramMap.get("size"));
                size = parsedSize > 150 ? 150 : parsedSize;
            } catch (NumberFormatException e) {}
        }

        return PageRequest.of(page, size, sort);
    }
}

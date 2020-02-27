package com.chauyiu1994.onlineBidProductsService.repositories.builders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SQLBuildEntity {

    public enum MAP_TYPE {
        NO,
        EQUAL_MAP,
        REGEX_MAP,
        GT_MAP,
        LT_MAP
    }

    private String tableName;
    private Map<String, Object> equalMap = new HashMap<>();
    private Map<String, String> regexMap = new HashMap<>();
    private Map<String, Object> gtMap = new HashMap<>();
    private Map<String, Object> ltMap = new HashMap<>();
    Pageable pageable;

    public void addEqual(String property, Object value) {

        equalMap.put(property, value);
    }

    public void addRegex(String property, String regex) {

        regexMap.put(property, regex);
    }

    public void addGT(String property, Object value) {

        gtMap.put(property, value);
    }

    public void addLT(String property, Object value) {

        ltMap.put(property, value);
    }

    public MAP_TYPE getLast() {

        if (ltMap.size() > 0) return MAP_TYPE.LT_MAP;
        if (gtMap.size() > 0) return MAP_TYPE.GT_MAP;
        if (regexMap.size() > 0) return MAP_TYPE.REGEX_MAP;
        if (equalMap.size() > 0) return MAP_TYPE.EQUAL_MAP;
        return MAP_TYPE.NO;
    }
}

package com.chauyiu1994.onlineBidProductsService.utils;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ParamMapParseUtil {

    public Integer parseIntValue(Map<String, String> paramMap, String key) {

        Integer value = null;
        if (paramMap.containsKey(key) && !paramMap.get(key).trim().equals("")) {
            try {
                value = Integer.parseInt(paramMap.get(key).trim());
            } catch (NumberFormatException e) {}
        }
        return value;
    }

    public Long parseLongValue(Map<String, String> paramMap, String key) {

        Long value = null;
        if (paramMap.containsKey(key) && !paramMap.get(key).trim().equals("")) {
            try {
                value = Long.parseLong(paramMap.get(key).trim());
            } catch (NumberFormatException e) {}
        }
        return value;
    }

    public String parseStringValue(Map<String, String> paramMap, String key) {

        String value = null;
        if (paramMap.containsKey(key) && !paramMap.get(key).trim().equals("")) {
            value = paramMap.get(key).trim().replace("-", "_").toUpperCase();
        }
        return value;
    }

    public Float parseFloatValue(Map<String, String> paramMap, String key) {

        Float value = null;
        if (paramMap.containsKey(key) && !paramMap.get(key).trim().equals("")) {
            try {
                value = Float.parseFloat(paramMap.get(key).trim());
            } catch (NumberFormatException e) {}
        }
        return value;
    }

    public String[] parseArrayValue(Map<String, String> paramMap, String key) {

        String[] value = null;
        if (paramMap.containsKey(key) && !paramMap.get(key).trim().equals("")) {
            value = paramMap.get("q").split("[+]");
        }
        return value;
    }

    public boolean parseBooleanValue(Map<String, String> paramMap, String key) {

        boolean value = false;
        if (paramMap.containsKey(key) && paramMap.get(key).toLowerCase().trim().equals("yes")) {
            value = true;
        }
        return value;
    }
}

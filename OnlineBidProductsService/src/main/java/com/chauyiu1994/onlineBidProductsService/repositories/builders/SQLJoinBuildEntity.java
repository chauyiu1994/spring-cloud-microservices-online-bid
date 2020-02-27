package com.chauyiu1994.onlineBidProductsService.repositories.builders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SQLJoinBuildEntity extends SQLBuildEntity {

    private boolean isLefterOuterJoin;
    private String toTableName;
    private Map<String, String> requiredFields = new HashMap<>();
    private Map<String, Object> childEqualMap = new HashMap<>();
    private Map<String, String> joinKeys = new HashMap<>();

    public void addRequiredFields(String field, String output) {

        requiredFields.put(field, output);
    }

    public void addChildEqual(String key, Object value) {

        childEqualMap.put(key, value);
    }

    public void addJoinKey(String fromTableField, String toTableField) {

        joinKeys.put(fromTableField, toTableField);
    }
}

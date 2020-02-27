package com.chauyiu1994.onlineBidProductsService.repositories;

import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuildEntity;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLBuilder;
import com.chauyiu1994.onlineBidProductsService.repositories.builders.SQLJoinBuildEntity;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

class SQLBuilderTest {

    private SQLBuilder sqlBuilder;

    SQLBuilderTest() {
        sqlBuilder = new SQLBuilder();
    }

    @Test
    void buildJoinSQL() {

        SQLJoinBuildEntity entity = new SQLJoinBuildEntity();

        entity.setTableName("PRODUCT");

        Map<String, Object> equalMap = new HashMap<>();
        equalMap.put("name", "mary");
        equalMap.put("price", 12);
        equalMap.put("id", 30);
        equalMap.put("category", "toy");
        entity.setEqualMap(equalMap);

        Map<String, String> regexMap = new HashMap<>();
        regexMap.put("name", "fish|boy");
        entity.setRegexMap(regexMap);

        Map<String, Object> gtMap = new HashMap<>();
        gtMap.put("price", 12);
        gtMap.put("time", LocalDateTime.now());
        gtMap.put("name", "hello");
        entity.setGtMap(gtMap);

        Map<String, Object> ltMap = new HashMap<>();
        ltMap.put("price", 12);
        ltMap.put("time", LocalDateTime.now());

        ltMap.put("name", "hello");
        entity.setLtMap(ltMap);

        Pageable pageable = PageRequest.of(3, 30, Sort.by("price").descending().and(Sort.by("lastModifiedTime").ascending()));
        entity.setPageable(pageable);

        entity.setToTableName("PRODUCT_LIKE");
        entity.addRequiredFields("FROM_USER_ID", "FROM_USER_ID");
        entity.addChildEqual("FROM_USER_ID", "1234");
        entity.addJoinKey("ID", "PRODUCT_ID");

        System.out.println(sqlBuilder.buildJoinSQL(entity));
    }

    @Test
    void buildSelectSQL() {

        SQLBuildEntity entity = new SQLBuildEntity();

        entity.setTableName("PRODUCT");

        Map<String, Object> equalMap = new HashMap<>();
        equalMap.put("name", "mary");
        equalMap.put("price", 12);
        equalMap.put("id", 30);
        equalMap.put("category", "toy");
        entity.setEqualMap(equalMap);

        Map<String, String> regexMap = new HashMap<>();
        regexMap.put("name", "fish|boy");
        entity.setRegexMap(regexMap);

        Map<String, Object> gtMap = new HashMap<>();
        gtMap.put("price", 12);
        gtMap.put("time", LocalDateTime.now());
        gtMap.put("name", "hello");
        entity.setGtMap(gtMap);

        Map<String, Object> ltMap = new HashMap<>();
        ltMap.put("price", 12);
        ltMap.put("time", LocalDateTime.now());

        ltMap.put("name", "hello");
        entity.setLtMap(ltMap);

        Pageable pageable = PageRequest.of(3, 30, Sort.by("price").descending().and(Sort.by("lastModifiedTime").ascending()));
        entity.setPageable(pageable);

        System.out.println(sqlBuilder.buildSelectSQL(entity));

        SQLBuildEntity entity1 = new SQLBuildEntity();
        entity1.setTableName("PRODUCT");
        System.out.println(sqlBuilder.buildSelectSQL(entity1));
    }

    @Test
    void buildEqualSQL() {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "mary");
        map.put("price", 12);
        map.put("id", 30);
        map.put("category", "toy");
        sqlBuilder.buildEqualSQL(stringBuilder, map, false, null);
        System.out.println(stringBuilder.toString());

    }

    @Test
    void buildRegexSQL() {

         StringBuilder stringBuilder = new StringBuilder();
         Map<String, String> map = new HashMap<>();
         map.put("name", "fish|boy");
         sqlBuilder.buildRegexSQL(stringBuilder, map, false, "");
         System.out.println(stringBuilder.toString());

         StringBuilder stringBuilder2 = new StringBuilder();
         Map<String, String> map2 = new HashMap<>();
         map2.put("name", "fish|boy");
         sqlBuilder.buildRegexSQL(stringBuilder2, map2, true, "");
         System.out.println(stringBuilder2.toString());
    }

    @Test
    void buildGTSQL() {

        StringBuilder stringBuilder = new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        map.put("price", 12);
        map.put("time", LocalDateTime.now());
        map.put("name", "hello");
        sqlBuilder.buildGTSQL(stringBuilder, map, false, "");
        System.out.println(stringBuilder.toString());
    }

    @Test
    void buildLTSQL() {

        StringBuilder stringBuilder = new StringBuilder();
        Map<String, Object> map = new HashMap<>();
        map.put("price", 12);
        map.put("time", LocalDateTime.now());
        map.put("name", "hello");
        sqlBuilder.buildLTSQL(stringBuilder, map, false, "");
        System.out.println(stringBuilder.toString());
    }

    @Test
    void builderSort() {

        StringBuilder stringBuilder = new StringBuilder();
        Pageable pageable = PageRequest.of(1, 10, Sort.by("price").descending().and(Sort.by("lastModifiedTime").ascending()));
        sqlBuilder.buildSortSQL(stringBuilder, pageable, "");

        System.out.println(stringBuilder.toString());
    }
}
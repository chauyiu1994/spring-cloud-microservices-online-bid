package com.chauyiu1994.onlineBidProductsService.repositories.builders;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;

@Component
public class SQLBuilder {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private void buildWhereCause(StringBuilder sqlBuilder, SQLBuildEntity entity) {

        if (entity.getLast() != SQLBuildEntity.MAP_TYPE.NO) sqlBuilder.append(" WHERE ");
        String mainIdentifier = (entity instanceof  SQLJoinBuildEntity) ? "A." : "";

        buildEqualSQL(sqlBuilder, entity.getEqualMap(), entity.getLast() == SQLBuildEntity.MAP_TYPE.EQUAL_MAP, mainIdentifier);
        buildRegexSQL(sqlBuilder, entity.getRegexMap(), entity.getLast() == SQLBuildEntity.MAP_TYPE.REGEX_MAP, mainIdentifier);
        buildGTSQL(sqlBuilder, entity.getGtMap(), entity.getLast() == SQLBuildEntity.MAP_TYPE.GT_MAP, mainIdentifier);
        buildLTSQL(sqlBuilder, entity.getLtMap(), entity.getLast() == SQLBuildEntity.MAP_TYPE.LT_MAP, mainIdentifier);
        buildSortSQL(sqlBuilder, entity.getPageable(), mainIdentifier);
    }

    public String buildStoredProcSQL(String procName, Long id) {

        return "CALL " + procName + "(" + id + ")";
    }

    public String buildSelectSQL(SQLBuildEntity entity)  {

        if (entity.getTableName() == null) return null;
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * FROM " + entity.getTableName());
        buildWhereCause(sqlBuilder, entity);

        return sqlBuilder.toString();
    }

    public String buildJoinSQL(SQLJoinBuildEntity entity) {

        if (entity.getTableName() == null
                || entity.getToTableName() == null
                || entity.getRequiredFields() == null || entity.getRequiredFields().size() == 0
                || entity.getJoinKeys() == null || entity.getJoinKeys().size() == 0) return null;

        StringBuilder sqlBuilder = new StringBuilder("SELECT A.*,  ");
        for (Iterator<Map.Entry<String, String>> it = entity.getRequiredFields().entrySet().iterator() ; it.hasNext() ; ) {
            Map.Entry<String, String> entry = it.next();
            String separator = it.hasNext() ? ", " : " ";
            sqlBuilder
                    .append("B.")
                    .append(entry.getKey())
                    .append(" AS ")
                    .append(entry.getValue())
                    .append(separator);
        }
        String joinStr = entity.isLefterOuterJoin() ? " LEFT OUTER JOIN " : " INNER JOIN ";
        sqlBuilder
                .append("FROM ")
                .append(entity.getTableName())
                .append(" A")
                .append(joinStr)
                .append(entity.getToTableName())
                .append(" B ON ");

        for (Iterator<Map.Entry<String, String>> it = entity.getJoinKeys().entrySet().iterator() ; it.hasNext() ; ) {
            Map.Entry<String, String> entry = it.next();
            String separator = it.hasNext() ? " AND " : " ";
            sqlBuilder
                    .append("A.")
                    .append(entry.getKey())
                    .append(" = B.")
                    .append(entry.getValue())
                    .append(separator);
        }

        sqlBuilder.append(" AND ");
        buildEqualSQL(sqlBuilder, entity.getChildEqualMap(), true, "B.");
        buildWhereCause(sqlBuilder, entity);

        return sqlBuilder.toString();
    }

    public void buildEqualSQL(StringBuilder sqlBuilder, Map<String, Object> equalMap, boolean isEnd, String identifier) {

        if (equalMap!= null && equalMap.entrySet().size() > 0) {

            for (Iterator<Map.Entry<String, Object>> it = equalMap.entrySet().iterator(); it.hasNext() ; ) {
                Map.Entry<String, Object> entry = it.next();
                if (entry.getValue() instanceof String) {
                    sqlBuilder
                            .append(identifier)
                            .append(entry.getKey()).append(" = '")
                            .append((String) entry.getValue())
                            .append("' ");
                } else if (entry.getValue() instanceof Number) {
                    sqlBuilder
                            .append(identifier)
                            .append(entry.getKey()).append(" = ")
                            .append(entry.getValue().toString())
                            .append(" ");
                } else {
                    continue;
                }
                if (isEnd && !it.hasNext()) break;
                sqlBuilder.append("AND ");
            }
        }
    }

    public void buildRegexSQL(StringBuilder sqlBuilder, Map<String, String> regexMap, boolean isEnd, String identifier) {

        if (regexMap!= null && regexMap.entrySet().size() > 0) {
            for (Iterator<Map.Entry<String, String>> it = regexMap.entrySet().iterator() ; it.hasNext() ; ) {
                Map.Entry<String, String> entry = it.next();
                sqlBuilder
                        .append(identifier)
                        .append(entry.getKey())
                        .append(" REGEXP '")
                        .append(entry.getValue())
                        .append("' ");
                if (isEnd && !it.hasNext()) break;
                sqlBuilder.append("AND ");
            }
        }
    }

    public void buildGTSQL(StringBuilder sqlBuilder, Map<String, Object> gtMap, boolean isEnd, String identifier) {

        if (gtMap!= null && gtMap.entrySet().size() > 0) {
            for (Iterator<Map.Entry<String, Object>> it = gtMap.entrySet().iterator() ; it.hasNext() ; ) {
                Map.Entry<String, Object> entry = it.next();
                if (entry.getValue() instanceof String) {
                    sqlBuilder
                            .append(identifier)
                            .append(entry.getKey())
                            .append(" >= '")
                            .append((String) entry.getValue())
                            .append("' ");
                } else if (entry.getValue() instanceof Number) {
                    sqlBuilder
                            .append(identifier)
                            .append(entry.getKey())
                            .append(" >= ")
                            .append(entry.getValue().toString())
                            .append(" ");
                } else if (entry.getValue() instanceof LocalDateTime) {
                    String formatDateTime = ((LocalDateTime) entry.getValue()).format(formatter);
                    sqlBuilder
                            .append(identifier)
                            .append(entry.getKey())
                            .append(" >= '")
                            .append(formatDateTime)
                            .append("' ");
                } else {
                    continue;
                }
                if (isEnd && !it.hasNext()) break;
                sqlBuilder.append("AND ");
            }
        }
    }

    public void buildLTSQL(StringBuilder sqlBuilder, Map<String, Object> ltMap, boolean isEnd, String identifier) {

        if (ltMap!= null && ltMap.entrySet().size() > 0) {
            for (Iterator<Map.Entry<String, Object>> it = ltMap.entrySet().iterator() ; it.hasNext() ; ) {
                Map.Entry<String, Object> entry = it.next();
                if (entry.getValue() instanceof String) {
                    sqlBuilder
                            .append(identifier)
                            .append(entry.getKey())
                            .append(" <= '")
                            .append((String) entry.getValue())
                            .append("' ");
                } else if (entry.getValue() instanceof Number) {
                    sqlBuilder
                            .append(identifier)
                            .append(entry.getKey())
                            .append(" <= ")
                            .append(entry.getValue().toString())
                            .append(" ");
                } else if (entry.getValue() instanceof LocalDateTime) {
                    String formatDateTime = ((LocalDateTime) entry.getValue()).format(formatter);
                    sqlBuilder
                            .append(identifier)
                            .append(entry.getKey())
                            .append(" <= '")
                            .append(formatDateTime)
                            .append("' ");
                } else {
                    continue;
                }
                if (isEnd && !it.hasNext()) break;
                sqlBuilder.append("AND ");
            }
        }
    }

    public void buildSortSQL(StringBuilder sqlBuilder, Pageable pageable, String identifier) {

        if (pageable == null) return;
        sqlBuilder.append(" ORDER BY ");
        long offset = pageable.getOffset();
        long limit = pageable.getPageSize();
        for (Iterator<Sort.Order> it = pageable.getSort().iterator() ; it.hasNext() ; ) {

            Sort.Order order = it.next();
            sqlBuilder
                    .append(identifier)
                    .append(order.getProperty());
            if (order.getDirection() == Sort.Direction.DESC) sqlBuilder.append(" DESC");
            if (it.hasNext()) sqlBuilder.append(", ");
        }
        sqlBuilder
                .append(" LIMIT ")
                .append(offset)
                .append(", ")
                .append(limit);
    }
}

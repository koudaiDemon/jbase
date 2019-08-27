package com.cwww.test;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cWww
 * @Title SearchQueryRow
 * @Description 查询solr自定义pojo
 * @date: 2018/11/15  10:35
 */
public class SearchQueryRow implements Serializable {
    public static final String ALL_FIELDS = "*";
    private String field;
    private String values;
    private QueryOperator queryOperator;
    private Operator operator;
    private List<SearchQueryRow> childs;
    private boolean isnull;

    public SearchQueryRow(String field) {
        this.field = field;
        this.childs = new ArrayList<>();
        queryOperator = QueryOperator.EQUAL_TO;
        operator = Operator.AND;
        isnull = false;
        values = ALL_FIELDS;
    }

    public SearchQueryRow(String field, String values) {
        this(field);
        this.values = values;
    }

    public SearchQueryRow(String field, QueryOperator queryOperator, String values) {
        this(field,values);
        this.queryOperator = queryOperator;
    }

    public SearchQueryRow(String field, Operator operator, QueryOperator queryOperator, String values) {
        this(field,values);
        this.operator = operator;
        this.queryOperator = queryOperator;
    }

    public void addChild(SearchQueryRow... rows) {
        this.childs.addAll(Arrays.asList(rows));
    }

    public boolean isIsnull() {
        return isnull;
    }

    public void setIsnull(boolean isnull) {
        this.isnull = isnull;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public QueryOperator getQueryOperator() {
        return queryOperator;
    }

    public void setQueryOperator(QueryOperator queryOperator) {
        this.queryOperator = queryOperator;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public List<SearchQueryRow> getChilds() {
        return childs;
    }

    public void setChilds(List<SearchQueryRow> childs) {
        this.childs = childs;
    }

    public static enum Operator {
        /**
         *
         */
        AND(" AND "),
        OR(" OR ");

        private final String name;

        private Operator(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public static enum QueryOperator {
        /**
         *
         */
        GREATER_THAN_OR_EQUAL_TO,
        LESS_THAN_OR_EQUAL_TO,
        GREATER_THAN,
        LESS_THAN,
        EQUAL_TO,
        CONTAINS,
        MATCHES;

        private QueryOperator() {
        }
    }

}

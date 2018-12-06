package com.cwww.test;

import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/11/15  11:31
 */
public class Demo {

    public static void createQueryRawsString(StringBuilder query, List<SearchQueryRow> searchQueryRows) {
        if (CollectionUtils.isNotEmpty(searchQueryRows)) {
            int count = 0;
            for (SearchQueryRow searchQueryRow : searchQueryRows) {
                count++;
                boolean hasChild = false;
                if (CollectionUtils.isNotEmpty(searchQueryRow.getChilds())) {
                    hasChild = true;
                }
                if (hasChild) {
                    query.append("(");
                }
                createQueryRawString(query, searchQueryRow);
                if (hasChild) {
                    query.append(")");
                }
                if (count < searchQueryRows.size()) {
                    query.append(searchQueryRow.getOperator().getName());
                }
            }
        }
    }

    public static void createQueryRawString(StringBuilder query,  SearchQueryRow searchQueryRow){

        final String field = searchQueryRow.getField();

        boolean hasChild = false;
        if (CollectionUtils.isNotEmpty(searchQueryRow.getChilds())) {
            hasChild = true;
        }
        if (!hasChild) {
            query.append("(");
            if (searchQueryRow.isIsnull()) {
                query.append("-");
                query.append(field);
            } else {
                query.append(field);
            }
            query.append(':');
            query.append(searchQueryRow.getValues());
            query.append(")");
        }
        if (hasChild) {
            final List<SearchQueryRow> childs = searchQueryRow.getChilds();
            createQueryRawsString(query, childs);
        }

    }

    public static void main(String[] args) {

//        SearchQueryRow searchQueryRow = new SearchQueryRow("aaa",SearchQueryRow.Operator.OR,SearchQueryRow.QueryOperator.EQUAL_TO,"bbb");
//        SearchQueryRow searchQueryRow2 = new SearchQueryRow("aaa",SearchQueryRow.Operator.AND,SearchQueryRow.QueryOperator.EQUAL_TO,"aaa");
//        SearchQueryRow searchQueryRow2 = new SearchQueryRow("aaa",SearchQueryRow.Operator.AND,SearchQueryRow.QueryOperator.EQUAL_TO,"aaa");
//
//        searchQueryRow.addChild(searchQueryRow2);
//        searchQueryRow.addChild(searchQueryRow2);
//        StringBuilder sb = new StringBuilder();
//
//
//
//        createQueryRawsString(sb,Arrays.asList(searchQueryRow,searchQueryRow2));
//
//        System.out.println(sb);



    }

}

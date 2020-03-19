package com.cwww.rule;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.collections.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2020/3/5
 */
@Data
@ToString
public class Condition {
    private Condition parent;
    private List<String> conditions;
    private Param param;

    public static Condition buildWithCriterion(Condition parent, Param param, String... criterion){
        final Condition condition = new Condition();
        condition.setParam(param);
        condition.setConditions(Arrays.asList(criterion));
        condition.setParent(parent);
        return condition;
    }

    public String build(){
        final StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isNotEmpty(conditions)) {
            final int size = conditions.size();
            if (1 != size) {
                sb.append("(");
            }
            for (int n = 0; n < size; n++) {
                if (n < size - 1) {
                    sb.append(conditions.get(n)).append(param.getValue());
                } else {
                    sb.append(conditions.get(n));
                }
            }
            if (1 != size) {
                sb.append(")");
            }
        }
        return sb.toString();
    }

    public enum Param{

        /**
         * 并且
         */
        AND(" 并且 "),
        /**
         * 或者
         */
        OR(" 或者 ");

        private String value;

        private Param(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}

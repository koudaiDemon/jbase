package com.cwww.rule;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private List<Condition> children;
    private List<Criterion> criteria;
    private AndOr andOr;

    public static Condition buildWithCriterion(List<Condition> children, AndOr andOr, Criterion... criteria){
        final Condition condition = new Condition();
        condition.setAndOr(andOr);
        condition.setCriteria(Arrays.asList(criteria));
        condition.setChildren(children);
        return condition;
    }

    public String build(){
        final StringJoiner sj = new StringJoiner(this.getAndOr().getValue(),"(",")");
        if (CollectionUtils.isEmpty(criteria)) {
            return "";
        }
        for (Criterion criterion : criteria) {
            sj.add(criterion.condition());
        }
        if (CollectionUtils.isNotEmpty(children)) {
            for (Condition child : children) {
                sj.add(child.build());
            }
        }
        return sj.toString();
    }

    @Data
    @Builder
    public static class Criterion {
        private String condition;
        private Object value;
        private String property;
        private Param param;

        public String condition() {
            return this.property + this.param.getValue() + this.value;
        }

    }

    public enum Param {
        /**
         * IN
         */
        IN(" IN "),
        /**
         * AND
         */
        EQUAL(" = ");

        private String value;

        private Param(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum AndOr {

        /**
         * 并且
         */
        AND(" && "),
        /**
         * 或者
         */
        OR(" || ");

        private String value;

        private AndOr(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}

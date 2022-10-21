package com.something.qlexpress;

import com.ql.util.express.Operator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * QL Express AnyIn
 *
 * @author wei.cai@hand-china.com 2020/4/28
 */
public class OperatorAnyIn extends Operator {
    private static final Integer PARAM_COUNT = 2;

    public OperatorAnyIn(String aName) {
        this.name = aName;
    }

    @Override
    public Object executeInner(Object[] list) throws Exception {
        final Object first = list[0];
        final Object second = list[1];
        final Collection<?> firstList = convertToList(first);
        final Collection<?> secondList = convertToList(second);
        return firstList.stream().anyMatch(secondList::contains);
    }

    /**
     * 转化成List
     * @param value 结果
     * @return List结果
     */
    private Collection<?> convertToList(Object value) {
        if (value instanceof String) {
            return Collections.singletonList(value);
        }
        if (value.getClass().isArray()) {
            return Arrays.asList((Object[]) value);
        } else {
            return (Collection<?>) value;
        }
    }

    /**
     * 校验结果
     * @param value 结果
     * @return 是否成功
     */
    private boolean validateValue(Object value) {
        return value != null && (value instanceof String || value.getClass().isArray() || value instanceof Collection);
    }

}

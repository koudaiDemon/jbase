package com.cwww.rule;

import lombok.Data;
import lombok.ToString;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2020/3/5
 */
@Data
@ToString
public class Rule {
    private String code;
    private String condition;
    private String action;
}

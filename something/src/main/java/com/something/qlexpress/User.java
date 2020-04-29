package com.something.qlexpress;

import lombok.Builder;
import lombok.Data;

/**
 * Description
 *
 * @author wei.cai@hand-china.com 2020/4/10
 */
@Data
@Builder
public class User {
    private String username;
    private Integer age;
    private String sex;
}

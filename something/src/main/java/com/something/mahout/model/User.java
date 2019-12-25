package com.something.mahout.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author cWww
 * @Title User
 * @Description 用户
 * @date: 2019/10/11  14:57
 */
@Data
@ToString
public class User {
    private String id;
    private Gender gender;
    private Integer age;
    private String occupation;
}

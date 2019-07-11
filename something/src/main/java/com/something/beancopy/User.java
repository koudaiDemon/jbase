package com.something.beancopy;

import lombok.*;

import java.util.Date;

/**
 * @author cWww
 * @Title User
 * @Description pojo
 * @date: 2019/4/25  13:44
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private Integer age;
    private Date birth;
}

package com.something.beancopy;

import lombok.*;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2019/4/25  13:48
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String username;
    private String password;
    private Integer age;

}

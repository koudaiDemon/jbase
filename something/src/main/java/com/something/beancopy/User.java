package com.something.beancopy;

import lombok.*;

import java.util.Date;

/**
 * @author cWww
 * @Title User
 * @Description pojo
 * @date: 2019/4/25  13:44
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private Integer age;
    private Date birth;
    private Address address;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address {
        private String address;
    }
}

package com.something.mahout;

import com.alibaba.fastjson.JSON;
import com.something.mahout.model.Gender;
import com.something.mahout.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author cWww
 * @Title 测试
 * @Description 测试
 * @date: 2019/10/12  17:46
 */
@Slf4j
public class App2 {

    @Test
    public void test() {
        final User user = new User();
        user.setGender(Gender.F);
        user.setId("111111");
        final String json = JSON.toJSONString(user);
        log.info("json:[{}]", json);
        log.info("date:[{}]", JSON.parseObject(json, User.class));
    }

    @Test
    public void test1() {
        while (true) {
            try {
                String str = null;
                str.length();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

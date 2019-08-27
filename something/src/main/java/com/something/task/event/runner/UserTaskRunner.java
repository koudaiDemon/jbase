package com.something.task.event.runner;

import com.something.task.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/4  16:20
 */
@Service("userTaskRunner")
public class UserTaskRunner extends AbstractTaskRunner<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserTaskRunner.class);

    @Override
    public void handleData(Set<User> data) {
        System.out.println(data);
        data.forEach(e -> LOGGER.info("user:{}",e));
    }

}

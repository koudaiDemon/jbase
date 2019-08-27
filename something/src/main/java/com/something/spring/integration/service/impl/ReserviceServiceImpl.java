package com.something.spring.integration.service.impl;

import com.something.spring.integration.pojo.User;
import com.something.spring.integration.service.ReserviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/6  17:27
 */
@Service("reserviceServiceImpl")
public class ReserviceServiceImpl implements ReserviceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReserviceServiceImpl.class);

    @Override
    public String sayHello(User user) {
        LOGGER.info("user,{},{}",user.getUsername(),user.getPassword());
        return user.getUsername()+":"+user.getPassword();
    }

}

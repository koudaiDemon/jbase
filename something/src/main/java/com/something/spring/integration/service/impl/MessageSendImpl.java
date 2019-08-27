package com.something.spring.integration.service.impl;

import com.something.spring.integration.pojo.User;
import com.something.spring.integration.service.MessageSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * @author cWww
 * @Title
 * @Description
 * @date: 2018/9/6  17:35
 */
@Service("messageSendImpl")
public class MessageSendImpl implements MessageSend {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSendImpl.class);

    @Autowired
    private MessageChannel testChannel;

    @Override
    public void send() {
        LOGGER.info("messageSend!!!!!");
        User user = new User("test","123456");
        testChannel.send(MessageBuilder.withPayload(user).build());
    }

}

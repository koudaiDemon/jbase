package com.something.mq.rocket;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author cWww
 * @Title MQ消息生产者
 * @Description MQ消息生产者
 * @date: 2019/4/26  13:15
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
            DefaultMQProducer("SyncProducer");
        // Specify name server addresses.
        producer.setNamesrvAddr("47.99.110.190:9876");
        //Launch the instance.
        producer.start();
//        for (int i = 0; i < 10; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTestA" /* Topic */,
                "TagA" /* Tag */,
                ("Hello RocketMQ 2019/4/28 ").getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
//        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
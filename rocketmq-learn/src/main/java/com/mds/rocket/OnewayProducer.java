package com.mds.rocket;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 单向发送
 * @author 13557
 */
public class OnewayProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("mds_ly_sdz");

        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        Message message = new Message("mds", "tags", "单向发送".getBytes());
        producer.sendOneway(message);
        Thread.sleep(2000);
        producer.shutdown();
    }
}

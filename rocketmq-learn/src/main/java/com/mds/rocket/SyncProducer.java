package com.mds.rocket;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 同步发送
 * @author 13557
 */
public class SyncProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        //1.创建消息生产者
        DefaultMQProducer producer = new DefaultMQProducer("mds");
        //2.设置nameserver地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        //3.开启
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message message = new Message("ly",
                    "lytag",
                    ("我的第一个消息" + 1).getBytes());
            SendResult result = producer.send(message);
            System.out.println(result);
        }

        //关闭
        producer.shutdown();
    }
}

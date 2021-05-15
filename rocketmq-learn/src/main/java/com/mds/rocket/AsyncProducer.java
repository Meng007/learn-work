package com.mds.rocket;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 发送异步消息
 * @author 13557
 */
public class AsyncProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("mds");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        for (int i = 0; i < 1000; i++) {
            Message message = new Message("sdz",
                    "sdztag",
                    ("我的异步发送" + i).getBytes());
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送成功！");
                    System.out.println(sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("发送失败！");
                    System.out.println(throwable.getMessage());
                }
            });
        }

        Thread.sleep(20000);
        producer.shutdown();
    }
}

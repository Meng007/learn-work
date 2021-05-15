package com.mds.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author 13557
 */
public class ZookeeperConnection {
    public static void main(String[] args) throws IOException, InterruptedException {
        CountDownLatch count = new CountDownLatch(0);
        // arg1:服务器的ip和端口
        // arg2:客户端与服务器之间的会话超时时间 以毫秒为单位的
        // arg3:监视器对象
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 500, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState().equals(Event.KeeperState.SyncConnected)){
                    System.out.println("连接zookeeper成功！");
                    count.countDown();
                }
            }
        });

        count.await();
        System.out.println(zooKeeper.getSessionId());
        zooKeeper.close();
    }
}

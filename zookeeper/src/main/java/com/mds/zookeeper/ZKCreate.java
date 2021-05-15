package com.mds.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.acl.Acl;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author 13557
 */
public class ZKCreate {

    private ZooKeeper zooKeeper;
    private String host = "127.0.0.1:2181";
    private int timeout = 5000;
    private CountDownLatch count  = new CountDownLatch(1);
    @Before
    public void init() throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(host, timeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState().equals(Event.KeeperState.SyncConnected)){
                    System.out.println("zookeeper连接成功！");
                    count.countDown();
                }

            }
        });
        //主线程堵塞，等待连接成功
        count.await();

    }

    @After
    public void after(){
        System.out.println("zookeeper关闭！");
    }

    @Test
    public void create1() throws KeeperException, InterruptedException {
        // arg1:节点的路径
        // arg2:节点的数据
        // arg3:权限列表 world:anyone:cdrwa
        // arg4:节点类型 持久化节点
        String s = zooKeeper.create("/admin/user", "张三".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.println(s);
    }

    @Test
    public void create2() throws KeeperException, InterruptedException {
        // Ids.READ_ACL_UNSAFE world:anyone:r
        String s = zooKeeper.create("/admin/role",
                "李四".getBytes(),
                ZooDefs.Ids.READ_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.println(s);
    }

    @Test
    public void create3() throws KeeperException, InterruptedException {
        // world授权模式
        // 权限列表
        List<ACL> acls = new ArrayList<>();
        // 授权模式和授权对象
        Id id = new Id("world","anyone");
        // 权限设置
        acls.add(new ACL(ZooDefs.Perms.READ,id));
        acls.add(new ACL(ZooDefs.Perms.WRITE,id));
        String s = zooKeeper.create("/admin/create", "王五".getBytes(), acls, CreateMode.PERSISTENT);
        System.out.println(s);
    }

    @Test
    public void create4() throws KeeperException, InterruptedException {
        List<ACL> acls = new ArrayList<>();
        Id ip = new Id("ip", "127.0.0.1");
        acls.add(new ACL(ZooDefs.Perms.ALL,ip));
        String s = zooKeeper.create("/admin/create2", "模式4".getBytes(), acls, CreateMode.PERSISTENT);
        System.out.println(s);
    }
}

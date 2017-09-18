package com.asiainfo;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * zookeeper的hello world程序
 *
 * @author zhangzhiwang
 * @date 2017年8月21日 下午3:28:53
 */
public class Zk1 {
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zooKeeper = new ZooKeeper("192.168.181.157:2181", 10000, new Watcher() {

			/*
			 * 回调函数，当被监听的节点发生变化时zookeeper调用此方法
			 */
			public void process(WatchedEvent arg0) {
				System.out.println(arg0.getPath());
				System.out.println(arg0.getType());
			}
		});

//		zooKeeper.create("/zzw2", "张志旺".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//		zooKeeper.create("/zzw3", "张志旺".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
//		zooKeeper.create("/zzw4", "张志旺".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//		zooKeeper.create("/zzw5", "张志旺".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		zooKeeper.create("/zzw5/zzw5_2", "张志旺".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
		zooKeeper.close();
	}
}

package com.asiainfo.serviceRegistAndFound;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * 服务器注册与发现——模拟服务端
 *
 * @author zhangzhiwang
 * @date 2017年8月22日 下午1:05:42
 */
public class Server {
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zooKeeper = new ZooKeeper("192.168.181.158:2181", 1000, null);
		if(zooKeeper.exists("/servers", false) == null) {
			zooKeeper.create("/servers", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}
		
		String stat = zooKeeper.create("/servers/", (args[0] + ":" + args[1]).getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println(args[0] + ":" + args[1] + " registed.");
		System.out.println("---------------------");
		Thread.sleep(Integer.MAX_VALUE);
	}
}

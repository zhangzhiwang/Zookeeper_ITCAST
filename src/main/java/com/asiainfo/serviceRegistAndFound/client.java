package com.asiainfo.serviceRegistAndFound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;

/**
 * 服务注册与发现——客户端模拟
 *
 * @author zhangzhiwang
 * @date 2017年8月22日 下午1:29:56
 */
public class client {
	private static ZooKeeper zooKeeper = null;
	private static List<String> servers;
	
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		zooKeeper = new ZooKeeper("192.168.181.158:2181", 1000, new Watcher() {
			
			public void process(WatchedEvent arg0) {
				if(arg0.getType() == EventType.None) {
					return;
				}
				try {
					getServerList();
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		getServerList();
		Thread.sleep(Integer.MAX_VALUE);
	}
	
	private static void getServerList() throws KeeperException, InterruptedException {
		List<String> children = zooKeeper.getChildren("/servers", true);
		servers = new ArrayList<String>();
		for(String child : children) {
			byte[] bs = zooKeeper.getData("/servers/" + child, false, null);
			servers.add(new String(bs));
		}

		System.out.println(servers);
		System.out.println("------------------");
	}
}

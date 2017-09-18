package com.asiainfo;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;

/**
 * zookeeper的增删改查以及监听
 *
 * @author zhangzhiwang
 * @date 2017年8月21日 下午4:41:42
 */
public class Zk2 {
	private static ZooKeeper zooKeeper;
	
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		zooKeeper = new ZooKeeper("192.168.181.157:2181", 10000, new Watcher() {

			/*
			 * 回调函数，默认只能使用一次
			 */
			public void process(WatchedEvent arg0) {
				if (arg0.getType() == EventType.None) {
					return;
				}
				System.out.println(arg0.getType());
				System.out.println(arg0.getPath());
				try {
					zooKeeper.getData("/zzw1", true, null);
					zooKeeper.getChildren("/zzw1", true);
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// 增
//		String str = zooKeeper.create("/zzw1", "aaa".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//		System.out.println("str = " + str);

		//改
//		zooKeeper.setData("/zzw1", "bbb".getBytes(), -1);//-1表示匹配所有版本 
		
		//查
//		byte[] bs = zooKeeper.getData("/zzw1", false, null);
//		System.out.println(new String(bs));
		
//		List<String> list = zooKeeper.getChildren("/zzw1", false);//查询某节点下的子节点
//		System.out.println(list);
		
		//删
//		Stat stat = zooKeeper.exists("/zzw1/zzw11", false);
//		System.out.println(stat);
//		zooKeeper.delete("/zzw1/zzw11", -1);
//		stat = zooKeeper.exists("/zzw1/zzw11", false);
//		System.out.println(stat);
		
		//监听
		zooKeeper.getData("/zzw1", true, null);//只监听节点数据的变化
		zooKeeper.getChildren("/zzw1", true);//只监听子节点的变化（不是子节点数据的变化）
		Thread.sleep(Integer.MAX_VALUE);
		
		zooKeeper.close();
	}
}

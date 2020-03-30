package zookeeper;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.AsyncCallback.VoidCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperBase {

	//zookeeper连接地址，多个用“,”分割
	static final String CONNECTION_ADDR="192.168.75.128:2181";
	//session超时时间
	static final int SESSION_OUTTIME=5000;
	/*阻塞程序使用，用于等待zookeeper连接成功，发送成功信号*/
	static final CountDownLatch connectedSemaphore=new CountDownLatch(1);
	
	static final CountDownLatch connectedSemaphore2=new CountDownLatch(1);
	
	public static void main(String[] args) throws Exception {
		//创建客户端对象是就开始了异步连接zookeeper
		ZooKeeper zookeeper=new ZooKeeper(CONNECTION_ADDR,SESSION_OUTTIME,new Watcher() {
			public void process(WatchedEvent event) {
				//获取事件状态
				KeeperState keeperState=event.getState();
				EventType eventType=event.getType();
				//如果是建立连接
				if(KeeperState.SyncConnected==keeperState) {
					if(EventType.None==eventType) {
						//如果是建立连接成功，则发送信号量，让后续阻塞程序向下执行
						connectedSemaphore.countDown();//放开connectedSemaphore.await();的阻塞，让程序继续执行
						System.out.println("zk 建立连接");
						
					}
				}
			}
		});
		//进行阻塞
		connectedSemaphore.await();
		
		
		//创建父节点，重复创建会报异常
		//zookeeper.create("/testRoot", "testRoot".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		//创建子节点
		/**
		 * CreateMode.EPHEMERAL:临时子节点，当一次会话完成之后被销毁，可用于分布式锁
		 * 
		 * 一个时间点至允许一个客户端去查看节点信息，通过判断节点信息是否存在来做到一个时间点内
		 * 处理一个请求
		 */
		System.out.println(zookeeper.create("/testRoot/subRoot", "subroot".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL));
		System.out.println(zookeeper.create("/testRoot/subRoot1", "subroot1".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL));
		System.out.println(zookeeper.create("/testRoot/subRoot2", "subroot2".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL));
		System.out.println(zookeeper.create("/testRoot/subRoot3", "subroot3".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL));
		/**
		 * 获取节点信息
		 */
		byte[] nodeDate=zookeeper.getData("/testRoot/subRoot", false, null);
		System.out.println("获取的节点数据"+new String(nodeDate));
		/**
		 * 创建节点和删除节点中都有两套api来创建节点，分别是同步的形式和异步的形式，其中异步的形式效率比较高
		 */
		/**
		 * 获取某个子节点下面的所有的节点数据
		 */
		
		List<String> list=zookeeper.getChildren("/testRoot", false);
		if(list!=null&&list.size()>0) {
			for(String path:list) {
				System.out.println("/testRoot/"+path+"路径下的数据为："+new String(zookeeper.getData("/testRoot/"+path, false, null)));
			}
		}else {
			System.out.println("该节点下面无子节点");
		}
		
		/**
		 * 异步删除
		 * path：节点的路径
		 * version：数据的版本号，每创建一个节点都会有一个数据版本号，通过get查看数据时可以看到（dataversion），
		 * -1表示跳过版本检查
		 * cb：回调函数
		 */
		zookeeper.delete("/testRoot/subRoot", -1, new VoidCallback() {
			
			public void processResult(int rc, String path, Object ctx) {
				System.out.println(rc);
				System.out.println(path);
				System.out.println(ctx);
			}
		}, "a");
		zookeeper.close();
	}
	
}

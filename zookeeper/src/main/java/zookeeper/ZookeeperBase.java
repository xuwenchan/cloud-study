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

	//zookeeper���ӵ�ַ������á�,���ָ�
	static final String CONNECTION_ADDR="192.168.75.128:2181";
	//session��ʱʱ��
	static final int SESSION_OUTTIME=5000;
	/*��������ʹ�ã����ڵȴ�zookeeper���ӳɹ������ͳɹ��ź�*/
	static final CountDownLatch connectedSemaphore=new CountDownLatch(1);
	
	static final CountDownLatch connectedSemaphore2=new CountDownLatch(1);
	
	public static void main(String[] args) throws Exception {
		//�����ͻ��˶����ǾͿ�ʼ���첽����zookeeper
		ZooKeeper zookeeper=new ZooKeeper(CONNECTION_ADDR,SESSION_OUTTIME,new Watcher() {
			public void process(WatchedEvent event) {
				//��ȡ�¼�״̬
				KeeperState keeperState=event.getState();
				EventType eventType=event.getType();
				//����ǽ�������
				if(KeeperState.SyncConnected==keeperState) {
					if(EventType.None==eventType) {
						//����ǽ������ӳɹ��������ź������ú���������������ִ��
						connectedSemaphore.countDown();//�ſ�connectedSemaphore.await();���������ó������ִ��
						System.out.println("zk ��������");
						
					}
				}
			}
		});
		//��������
		connectedSemaphore.await();
		
		
		//�������ڵ㣬�ظ������ᱨ�쳣
		//zookeeper.create("/testRoot", "testRoot".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		//�����ӽڵ�
		/**
		 * CreateMode.EPHEMERAL:��ʱ�ӽڵ㣬��һ�λỰ���֮�����٣������ڷֲ�ʽ��
		 * 
		 * һ��ʱ���������һ���ͻ���ȥ�鿴�ڵ���Ϣ��ͨ���жϽڵ���Ϣ�Ƿ����������һ��ʱ�����
		 * ����һ������
		 */
		System.out.println(zookeeper.create("/testRoot/subRoot", "subroot".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL));
		System.out.println(zookeeper.create("/testRoot/subRoot1", "subroot1".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL));
		System.out.println(zookeeper.create("/testRoot/subRoot2", "subroot2".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL));
		System.out.println(zookeeper.create("/testRoot/subRoot3", "subroot3".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL));
		/**
		 * ��ȡ�ڵ���Ϣ
		 */
		byte[] nodeDate=zookeeper.getData("/testRoot/subRoot", false, null);
		System.out.println("��ȡ�Ľڵ�����"+new String(nodeDate));
		/**
		 * �����ڵ��ɾ���ڵ��ж�������api�������ڵ㣬�ֱ���ͬ������ʽ���첽����ʽ�������첽����ʽЧ�ʱȽϸ�
		 */
		/**
		 * ��ȡĳ���ӽڵ���������еĽڵ�����
		 */
		
		List<String> list=zookeeper.getChildren("/testRoot", false);
		if(list!=null&&list.size()>0) {
			for(String path:list) {
				System.out.println("/testRoot/"+path+"·���µ�����Ϊ��"+new String(zookeeper.getData("/testRoot/"+path, false, null)));
			}
		}else {
			System.out.println("�ýڵ��������ӽڵ�");
		}
		
		/**
		 * �첽ɾ��
		 * path���ڵ��·��
		 * version�����ݵİ汾�ţ�ÿ����һ���ڵ㶼����һ�����ݰ汾�ţ�ͨ��get�鿴����ʱ���Կ�����dataversion����
		 * -1��ʾ�����汾���
		 * cb���ص�����
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

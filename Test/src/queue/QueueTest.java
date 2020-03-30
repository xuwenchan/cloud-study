package queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class QueueTest {
	
	public static void main(String[] args) {
		ConcurrentLinkedQueueTest();
		BlockingQueueTest();
	}
	
	/**
	 * ConcurrentLinkedQueue ���е�ʹ��
	 * 
	 * 	ConcurrentLinkedQueue : 
	 * ��һ�������ڸ߲��������µĶ��У�ͨ�������ķ�ʽ��ʵ���˸߲���״̬�µĸ����ܣ�
	 * ͨ��ConcurrentLinkedQueue���ܺ���BlockingQueue�� 
	 * ����һ���������ӽڵ���޽��̰߳�ȫ���С��ö��е�Ԫ����ѭ�Ƚ��ȳ���ԭ�� 
	 * ͷ�����ȼ���ģ�β���������ģ��ö��в�����nullԪ�ء�
	 */
	public static void ConcurrentLinkedQueueTest() {
		ConcurrentLinkedQueue<String> concurrentLinkedQueue=new ConcurrentLinkedQueue<String>();
		concurrentLinkedQueue.add("1111");
		concurrentLinkedQueue.add("2222");
		concurrentLinkedQueue.add("3333");
		concurrentLinkedQueue.add("4444");
		concurrentLinkedQueue.add("5555");
		concurrentLinkedQueue.add("6666");
		
		System.out.println(concurrentLinkedQueue.size());
		while(!concurrentLinkedQueue.isEmpty()) {
			String content=concurrentLinkedQueue.poll();
			System.out.println(content);
		}
	}
	
	/**
	 * ���壺 
		�������У�BlockingQueue����һ��֧���������Ӳ����Ķ��С����������ӵĲ����ǣ� 
		1���ڶ���Ϊ��ʱ����ȡԪ�ص��̻߳�ȴ����б�Ϊ�ǿա� 
		2����������ʱ���洢Ԫ�ص��̻߳�ȴ����п��á� 
		�����������̰߳�ȫ�ġ� 
		��;�� 
		�������г����������ߺ������ߵĳ������������������������Ԫ�ص��̣߳�
		�������ǴӶ�������Ԫ�ص��̡߳�
		�������о��������ߴ��Ԫ�ص���������������Ҳֻ����������Ԫ�ء�
		
		ArrayBlockingQueue��
		add(E e) 
		��ָ����Ԫ�ز��뵽�˶��е�β����������������Ҳ��ᳬ���ö��е���������
		�ڳɹ�ʱ���� true������˶������������׳�IllegalStateException�� 
		offer(E e) 
		��ָ����Ԫ�ز��뵽�˶��е�β����������������Ҳ��ᳬ���ö��е���������
		�ڳɹ�ʱ���� true������˶����������򷵻� false�� 
		offer(E e, long timeout, TimeUnit unit) 
		��ָ����Ԫ�ز���˶��е�β��������ö���������
		���ڵ���ָ���ĵȴ�ʱ��֮ǰ�ȴ����õĿռ䡣
		
	 */
	public static void BlockingQueueTest() {
		/**
		 * ���캯����ֵΪ����
		 */
		ArrayBlockingQueue<String> arrayBlockQueue=new ArrayBlockingQueue<String>(20);
		arrayBlockQueue.add("AKJLFDJA");
		arrayBlockQueue.add("AKJLFDJA");
		arrayBlockQueue.add("AKJLFDJA");
		arrayBlockQueue.add("AKJLFDJA");
		arrayBlockQueue.add("AKJLFDJA");
		
		while(!arrayBlockQueue.isEmpty()) {
			String content=arrayBlockQueue.poll();
			System.out.println(content);
		}
		
	}
	
	/**
	 * LinkedBlockingQueue
	 * 
	 * LinkedBlockingQueue�������д�С�������ǿ�ѡ�ģ� 
		������ǳ�ʼ��ʱָ��һ����С���������б߽�ģ������ָ�����������ޱ߽�ġ� 
		˵���ޱ߽磬��ʵ�ǲ�����Ĭ�ϴ�СΪInteger.MAX_VALUE������ ��
		�����ڲ�ʵ����һ������ 
		��ArrayBlockingQueueһ����LinkedBlockingQueue Ҳ�����Ƚ��ȳ��ķ�ʽ�洢���ݣ�
		���²���Ķ�����β���������Ƴ��Ķ�����ͷ����
	 */
	public static void LinkedBlockingQueueTest() {
		//��ʼ��
		LinkedBlockingQueue lbq = new LinkedBlockingQueue(3);
		        lbq.add("����");
		        lbq.add("����");
		        lbq.add("����");
		        System.out.println(lbq.size());
		        //���н����3
	}
	/**
	 * PriorityBlockingQueue��һ��û�б߽�Ķ��У�
	 * ������������ java.util.PriorityQueueһ����
	 * ��Ҫע�⣬PriorityBlockingQueue���������null���� 
		���в���PriorityBlockingQueue�Ķ������ʵ�� java.lang.Comparable�ӿڣ�
		�������ȼ������������ǰ������Ƕ�����ӿڵ�ʵ��������ġ� 
		���⣬���ǿ��Դ�PriorityBlockingQueue���һ��������Iterator��
		�����������������֤�������ȼ�˳����е�����
	 */
	public static void PriorityBlockingQueueTest() {
		//��ʼ��
		PriorityBlockingQueue lbq = new PriorityBlockingQueue(3);
				        lbq.add("����");
				        lbq.add("����");
				        lbq.add("����");
				        System.out.println(lbq.size());
				        //���н����3
	}
	/**
	 * SynchronousQueue�����ڲ�����������һ��Ԫ�ء�
	 * ��һ���̲߳���һ��Ԫ�غ�ᱻ�������������Ԫ�ر���һ���߳����ѡ�
	 */
	public static void SynchronousQueueTest() {
		
	}	
	

}

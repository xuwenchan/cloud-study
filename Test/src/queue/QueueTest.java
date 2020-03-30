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
	 * ConcurrentLinkedQueue 队列的使用
	 * 
	 * 	ConcurrentLinkedQueue : 
	 * 是一个适用于高并发场景下的队列，通过无锁的方式，实现了高并发状态下的高性能，
	 * 通常ConcurrentLinkedQueue性能好于BlockingQueue。 
	 * 它是一个基于链接节点的无界线程安全队列。该队列的元素遵循先进先出的原则。 
	 * 头是最先加入的，尾是最近加入的，该队列不允许null元素。
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
	 * 定义： 
		阻塞队列（BlockingQueue）是一个支持两个附加操作的队列。这两个附加的操作是： 
		1、在队列为空时，获取元素的线程会等待队列变为非空。 
		2、当队列满时，存储元素的线程会等待队列可用。 
		阻塞队列是线程安全的。 
		用途： 
		阻塞队列常用于生产者和消费者的场景，生产者是往队列里添加元素的线程，
		消费者是从队列里拿元素的线程。
		阻塞队列就是生产者存放元素的容器，而消费者也只从容器里拿元素。
		
		ArrayBlockingQueue：
		add(E e) 
		将指定的元素插入到此队列的尾部（如果立即可行且不会超过该队列的容量），
		在成功时返回 true，如果此队列已满，则抛出IllegalStateException； 
		offer(E e) 
		将指定的元素插入到此队列的尾部（如果立即可行且不会超过该队列的容量），
		在成功时返回 true，如果此队列已满，则返回 false。 
		offer(E e, long timeout, TimeUnit unit) 
		将指定的元素插入此队列的尾部，如果该队列已满，
		则在到达指定的等待时间之前等待可用的空间。
		
	 */
	public static void BlockingQueueTest() {
		/**
		 * 构造函数传值为容量
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
	 * LinkedBlockingQueue阻塞队列大小的配置是可选的， 
		如果我们初始化时指定一个大小，它就是有边界的，如果不指定，它就是无边界的。 
		说是无边界，其实是采用了默认大小为Integer.MAX_VALUE的容量 。
		它的内部实现是一个链表。 
		和ArrayBlockingQueue一样，LinkedBlockingQueue 也是以先进先出的方式存储数据，
		最新插入的对象是尾部，最新移出的对象是头部。
	 */
	public static void LinkedBlockingQueueTest() {
		//初始化
		LinkedBlockingQueue lbq = new LinkedBlockingQueue(3);
		        lbq.add("张三");
		        lbq.add("李四");
		        lbq.add("李四");
		        System.out.println(lbq.size());
		        //运行结果：3
	}
	/**
	 * PriorityBlockingQueue是一个没有边界的队列，
	 * 它的排序规则和 java.util.PriorityQueue一样。
	 * 需要注意，PriorityBlockingQueue中允许插入null对象。 
		所有插入PriorityBlockingQueue的对象必须实现 java.lang.Comparable接口，
		队列优先级的排序规则就是按照我们对这个接口的实现来定义的。 
		另外，我们可以从PriorityBlockingQueue获得一个迭代器Iterator，
		但这个迭代器并不保证按照优先级顺序进行迭代。
	 */
	public static void PriorityBlockingQueueTest() {
		//初始化
		PriorityBlockingQueue lbq = new PriorityBlockingQueue(3);
				        lbq.add("张三");
				        lbq.add("李四");
				        lbq.add("李四");
				        System.out.println(lbq.size());
				        //运行结果：3
	}
	/**
	 * SynchronousQueue队列内部仅允许容纳一个元素。
	 * 当一个线程插入一个元素后会被阻塞，除非这个元素被另一个线程消费。
	 */
	public static void SynchronousQueueTest() {
		
	}	
	

}

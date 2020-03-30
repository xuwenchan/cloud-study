package AtomicPackage;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class LongAddrTest {


	//��������
	public static int clientTotal=5000;
	//ͬʱ����ִ�е��߳���
	public static int threadTotal=200;
	
	//public static AtomicInteger count=new AtomicInteger(0);
	public static LongAdder count=new LongAdder();
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService=Executors.newCachedThreadPool();
		final Semaphore semaphore=new Semaphore(threadTotal);	
		final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
		for(int i=0;i<clientTotal;i++) {
			executorService.execute(()->{
				try {
					semaphore.acquire();
					add();
					semaphore.release();
				}catch(Exception e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		System.out.println("count:"+count);
	}
	
	public static void add() {
		//ԭ��������һ����ǰֵ�����������ȡ
		//count.incrementAndGet();
		count.increment();
		//ԭ��������һ����ǰֵ�� �Ȼ�ȡ������
	//	count.getAndIncrement();
	}
}

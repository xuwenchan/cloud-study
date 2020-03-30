package AtomicPackage;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import annoation.ThreadSafe;

@ThreadSafe
public class AtomicTest {
	
	//请求总数
	public static int clientTotal=5000;
	//同时并发执行的线程数
	public static int threadTotal=200;
	
	public static AtomicInteger count=new AtomicInteger(0);
	
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
		System.out.println("count:"+count.get());
	}
	
	public static void add() {
		//原子上增加一个当前值，先增长后获取
		count.incrementAndGet();
		//原子上增加一个当前值。 先获取后增长
	//	count.getAndIncrement();
	}
}

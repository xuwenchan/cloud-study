package AQS;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore的使用
 * Semaphore控制资源允许访问的线程个数
 * Semaphore有两个核心方法
 * acquire：获取一个许可，没有就一直等待
 * release：释放许可
 * 
 * 
 * @author 徐文产
 *
 */
public class SemaphoreExample {

	private final static int threadCount=2;
	
	private final static int requestCount=50;
	
	public static void main(String[] args) {
		
		ExecutorService executorService=Executors.newCachedThreadPool();
		Semaphore semaphore=new Semaphore(threadCount);
		for(int i=0;i<requestCount;i++) {
			executorService.execute(()->{
				try {
					semaphore.acquire();//获取一个许可
					service();
					semaphore.release();//释放许可
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			});
		}
		executorService.shutdown();
	}

	private static void service() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println(Thread.currentThread().getId());
	}
}

package AQS;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample {

	private static int threadCount=200;
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService=Executors.newCachedThreadPool();
		CountDownLatch countDownLatch=new CountDownLatch(threadCount);
		for(int i=0;i<threadCount;i++) {
			final int threadNum=i;
			executorService.execute(()->{
				try {
					test(threadNum);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					countDownLatch.countDown();
				}
				
			});
		}
		//使用场景，让任务在规定时间内做完，如果规定时间内做不完，不予理睬
		countDownLatch.await(10,TimeUnit.MILLISECONDS);
		executorService.shutdown();
	}
	
	private static void test(int threadNum) throws InterruptedException {
		Thread.sleep(100);
		System.out.println("----"+threadNum);
	}
}

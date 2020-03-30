package LockPackage;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

public class StampedLockTeste {

	public static int clientTotal=1000;
	public static int threadTotal=50;
	public static int count=0;
	
	public static StampedLock lock =new StampedLock();
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorServicee=Executors.newCachedThreadPool();
		//允许并发数
		final Semaphore semaphore=new Semaphore(threadTotal);
		final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
		for(int i=0;i<clientTotal;i++) {
			executorServicee.execute(()->{
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
		executorServicee.shutdown();
		System.out.println("count:"+count);
	}
	
	public static void add() {
		long stamp=lock.writeLock();
		count++;
		lock.unlock(stamp);
	}
}

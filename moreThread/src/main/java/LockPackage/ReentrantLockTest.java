package LockPackage;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock独有的功能
 * 1，可以指定是公平锁和非公平锁
 * 公平锁：先等待的线程先执行
 * 2，提供了一个Condition类，可以分组唤醒需要唤醒的线程
 * 3，提供能够中断 等待锁的线程的机制，lock.LockInterruptibly();
 * @author 徐文产
 *
 */
public class ReentrantLockTest {

	//请求总数
	public static int clientTotal=5000;
	//同时并发执行的线程数
	public static int threadTotal=200;

	public static int count=0;

	private final static Lock lock=new ReentrantLock();


	public static void main(String[] args) throws InterruptedException {

		CountDownLatch countDownLatch=new CountDownLatch(clientTotal);

		Semaphore semaphore=new Semaphore(threadTotal);

		ExecutorService executorServicee= Executors.newCachedThreadPool();
		executorServicee.execute(()->{
			try{
				semaphore.acquire();
				add();
				semaphore.release();
			}catch(Exception e){
				e.printStackTrace();
			}
		});
		countDownLatch.await();
		executorServicee.shutdown();
		System.out.println("count:"+count);

	}

	public static void add(){
		try{
			lock.lock();
			count++;
		}catch(Exception e){

		}finally {
			lock.unlock();
		}
	}
}

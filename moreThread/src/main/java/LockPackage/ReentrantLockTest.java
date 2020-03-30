package LockPackage;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock���еĹ���
 * 1������ָ���ǹ�ƽ���ͷǹ�ƽ��
 * ��ƽ�����ȵȴ����߳���ִ��
 * 2���ṩ��һ��Condition�࣬���Է��黽����Ҫ���ѵ��߳�
 * 3���ṩ�ܹ��ж� �ȴ������̵߳Ļ��ƣ�lock.LockInterruptibly();
 * @author ���Ĳ�
 *
 */
public class ReentrantLockTest {

	//��������
	public static int clientTotal=5000;
	//ͬʱ����ִ�е��߳���
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

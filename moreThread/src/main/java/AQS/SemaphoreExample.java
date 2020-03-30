package AQS;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore��ʹ��
 * Semaphore������Դ������ʵ��̸߳���
 * Semaphore���������ķ���
 * acquire����ȡһ����ɣ�û�о�һֱ�ȴ�
 * release���ͷ����
 * 
 * 
 * @author ���Ĳ�
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
					semaphore.acquire();//��ȡһ�����
					service();
					semaphore.release();//�ͷ����
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

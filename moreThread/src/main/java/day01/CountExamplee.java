package day01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * ����������̲߳�����ͬ����Դ����֤�̰߳�ȫ����ʹ����Դ
 * �߲�����������ͬʱ����ܶ�������߳�������
 * @author ���Ĳ�
 *
 */
public class CountExamplee {
	private static int threadTotal=200;
	private static int clientTotal=5000;
	
	private static long count=0;
	
	public static void main(String[] args) {
		
		ExecutorService exec=Executors.newCachedThreadPool();
		Semaphore  semaphore=new Semaphore(threadTotal);
		for(int index=0;index<clientTotal;index++) {
			exec.execute(()->{
				try {
					semaphore.acquire();
					add();
					semaphore.release();
				}catch(Exception e) {
					
				}
			});
		}
		exec.shutdown();//��������رգ�������ǰ�ύ�����񽫱�ִ�У�����������κ�������
		System.out.println("count:"+count);
	}
	
	public static void add() {
		count++;
	}

}

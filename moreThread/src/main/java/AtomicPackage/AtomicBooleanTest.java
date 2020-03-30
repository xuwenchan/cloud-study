package AtomicPackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * AtomicBoolean���ʹ��
 * @author ���Ĳ�
 *
 */
public class AtomicBooleanTest {

	private static int threadTotal=200;
	private static int clientTotal=5000;
	
	private static long count=0;
	
	private static AtomicBoolean ishappen=new AtomicBoolean(false);
	
	public static void main(String[] args) {
		
		ExecutorService exec=Executors.newCachedThreadPool();
		Semaphore  semaphore=new Semaphore(threadTotal);
		for(int index=0;index<clientTotal;index++) {
			exec.execute(()->{
				try {
					semaphore.acquire();
					test();
					semaphore.release();
				}catch(Exception e) {
					
				}
			});
		}
		exec.shutdown();//��������رգ�������ǰ�ύ�����񽫱�ִ�У�����������κ�������
		System.out.println("count:"+ishappen.get());
	}
	
	public static void add() {
		count++;
	}
	//��ĳһ�δ���ִֻ��һ�Σ����Բ���������ʽ
	public static void test() {
		if(ishappen.compareAndSet(false, true)) {
			System.out.println(ishappen.get());
		}
	}
	

}

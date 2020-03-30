package AtomicPackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * AtomicBoolean类的使用
 * @author 徐文产
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
		exec.shutdown();//启动有序关闭，其中先前提交的任务将被执行，但不会接受任何新任务。
		System.out.println("count:"+ishappen.get());
	}
	
	public static void add() {
		count++;
	}
	//让某一段代码只执行一次，可以采用这样方式
	public static void test() {
		if(ishappen.compareAndSet(false, true)) {
			System.out.println(ishappen.get());
		}
	}
	

}

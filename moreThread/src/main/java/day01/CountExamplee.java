package day01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 并发：多个线程操作相同的资源，保证线程安全合理使用资源
 * 高并发：服务能同时处理很多请求，提高程序性能
 * @author 徐文产
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
		exec.shutdown();//启动有序关闭，其中先前提交的任务将被执行，但不会接受任何新任务。
		System.out.println("count:"+count);
	}
	
	public static void add() {
		count++;
	}

}

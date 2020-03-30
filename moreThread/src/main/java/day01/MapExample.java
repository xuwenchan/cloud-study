package day01;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MapExample {

	private static int threadTotal=200;
	private static int clientTotal=5000;
	
	private static Map<Integer,Integer> map=new HashMap<Integer,Integer>();
	private static long count=0;
	
	public static void main(String[] args) {
		
		ExecutorService exec=Executors.newCachedThreadPool();
		Semaphore  semaphore=new Semaphore(threadTotal);
		for(int index=0;index<clientTotal;index++) {
			final int threadNum =index;
			exec.execute(()->{
				try {
					semaphore.acquire();
					func(threadNum);
					semaphore.release();
				}catch(Exception e) {
					
				}
			});
		}
		exec.shutdown();//启动有序关闭，其中先前提交的任务将被执行，但不会接受任何新任务。
		System.out.println("count:"+count);
	}
	
	private static void func(int threadNum) {
		map.put(threadNum,threadNum);
	}

	public static void add() {
		count++;
	}

}

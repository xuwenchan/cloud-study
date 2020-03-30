package AQS;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierExample1 {

	private static CyclicBarrier barrier=new CyclicBarrier(5);
	
	
	
	public static void main(String[] args) {
	
		ExecutorService executor=Executors.newCachedThreadPool();
		for(int i=0;i<5;i++) {
			final int threadNum=i;
			executor.execute(()->{
				try {
					race(threadNum);
				}catch(Exception e) {
					e.printStackTrace();
				}
			});
		}
		executor.shutdown();
	}



	private static void race(int threadNum) throws InterruptedException, BrokenBarrierException, TimeoutException {
		System.out.println("is ready"+ threadNum);
		barrier.await();
		System.out.println(threadNum+" is continue");
	}
}

package FuturePackage;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {

	static class myCallable implements Callable<String>{

		@Override
		public String call() throws Exception {
			System.out.println("线程正在执行");
			Thread.sleep(5000);
			return "end";
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor=Executors.newCachedThreadPool();
		Future<String> future= executor.submit(new myCallable());
		System.out.println(future.get());
		/*executor.execute(()->{
			
		});*/
		executor.shutdown();
	}
}

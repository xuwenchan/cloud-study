package FuturePackage;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> futureTask=new FutureTask<String>(new Callable<String>(){

			@Override
			public String call() throws Exception {
				Thread.sleep(5000);
				return "end";
			}
		});
		
		new  Thread(futureTask).start();
		System.out.println(futureTask.get());
	}
}

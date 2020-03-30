package FuturePackage;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/join
 * 是java7提供的一个并行执行任务的框架，
 * 把一个任务分割成若干个小任务，然后汇总小任务
 * @author 梦天涯
 *
 */
public class ForkJoinTest extends RecursiveTask<Integer>{

	public static final int threadShold=2;
	private int start;
	private int end;
	
	public ForkJoinTest(int a,int b) {
		this.start=a;
		this.end=b;
	}
	
	
	public static void main(String[] args) {
		ForkJoinPool forkjoinPool=new ForkJoinPool();
		//生成一个计算任务，就算1+2+3+4
		ForkJoinTest task=new ForkJoinTest(1,100);
		Future<Integer> result=forkjoinPool.submit(task);
		try {
			System.out.println("结果为：   "+result.get());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Integer compute() {
		int sum=0;
		boolean canCompute=(end-start)<=threadShold;
		if(canCompute) {
			for(int i=start;i<=end;i++) {
				sum=sum+i;
			}
		}else {
			//如果任务大于阈值，就分裂成两个子任务计算
			int middle=(start+end)/2;
			ForkJoinTest leftTask=new ForkJoinTest(start,middle);
			ForkJoinTest rightTask=new ForkJoinTest(middle+1,end);
			//执行子任务
			leftTask.fork();
			rightTask.fork();
			//等待任务执行结束合并其结果
			int leftResult=leftTask.join();
			int rightResult=rightTask.join();
			sum=leftResult+rightResult;
		}
		return sum;
	}
}

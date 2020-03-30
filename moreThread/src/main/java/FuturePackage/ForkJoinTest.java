package FuturePackage;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Fork/join
 * ��java7�ṩ��һ������ִ������Ŀ�ܣ�
 * ��һ������ָ�����ɸ�С����Ȼ�����С����
 * @author ������
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
		//����һ���������񣬾���1+2+3+4
		ForkJoinTest task=new ForkJoinTest(1,100);
		Future<Integer> result=forkjoinPool.submit(task);
		try {
			System.out.println("���Ϊ��   "+result.get());
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
			//������������ֵ���ͷ��ѳ��������������
			int middle=(start+end)/2;
			ForkJoinTest leftTask=new ForkJoinTest(start,middle);
			ForkJoinTest rightTask=new ForkJoinTest(middle+1,end);
			//ִ��������
			leftTask.fork();
			rightTask.fork();
			//�ȴ�����ִ�н����ϲ�����
			int leftResult=leftTask.join();
			int rightResult=rightTask.join();
			sum=leftResult+rightResult;
		}
		return sum;
	}
}

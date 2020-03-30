package LockPackage;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {

	public static void main(String[] args) {
		ReentrantLock reentrantLock=new ReentrantLock();
		Condition condition=reentrantLock.newCondition();
		
		new Thread(()->{
			try {
				reentrantLock.lock();
				System.out.println("wait signl 1");
				condition.await();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				reentrantLock.unlock();
			}
			System.out.println("get signl 2");
			
		}).start();
		
		new Thread( ()->{
			reentrantLock.lock();
			System.out.println("get Lock 3");
			try {
				Thread.sleep(3000);
			}catch(Exception e) {
				e.printStackTrace();
			}
			condition.signalAll();
			System.out.println("send signl 4");
			reentrantLock.unlock();
			}).start();
		}
}

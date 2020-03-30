package LockPackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Synchronized 的使用
 * @author 徐文产
 *	
 *原子性-synchronized
 *修饰代码块：大括号括起来的代码，作用于调用的对象
 *不同对象之间互不影响	
 *
 *修改方法：整个方法，作用于调用对象
 *不同对象之间互不影响
 *
 *
 *
 *修饰静态方法：整个静态方法，作用于所有对象
 *
 *修改类：括号括起来的部分，作用于所有对象
 *
 *需注意的是；
 *当一个子类继承了该类，调用了父类的同步方法，
 *是加不上synchronized的
 *
 *
 *
 */
public class SynchronizedTest {

	//修饰代码块
	public void test1() {
		synchronized(this) {
			for(int i=0;i<10;i++) {
				System.out.println("---"+i);
			}
		}
	}
	//修饰一个方法
	
	public synchronized void test2() {
		for(int i=0;i<10;i++) {
			System.out.println("---"+i);
		}
	}
	
	
	
	public static void main(String[] args) {
		SynchronizedTest test=new SynchronizedTest();
		ExecutorService executorService=Executors.newCachedThreadPool();
		executorService.execute(()->{
			test.test1();
		});
		
		executorService.execute(()->{
			test.test1();
		});
	}
}

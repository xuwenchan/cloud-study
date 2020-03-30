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
 * synchronized:不可中断锁，适合竞争不激烈，可读性好
 * 
 * 
 * lock：可中断锁，多样化同步，竞争激烈时能维持常态
 *
 *
 */
public class SynchronizedTest2 {

	//修饰代码块
	public static void test1() throws InterruptedException {
		synchronized(SynchronizedTest2.class) {
			for(int i=0;i<10;i++) {
				Thread.sleep(10);
				System.out.println("---"+i);
			}
		}
	}
	//修饰一个方法
	
	public static synchronized void test2() {
		for(int i=0;i<10;i++) {
			System.out.println("---"+i);
		}
	}
	
	
	
	public static void main(String[] args) {
		SynchronizedTest2 test=new SynchronizedTest2();
		SynchronizedTest2 test1=new SynchronizedTest2();
		ExecutorService executorService=Executors.newCachedThreadPool();
		executorService.execute(()->{
			try {
				test.test1();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		executorService.execute(()->{
			try {
				test1.test1();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
}

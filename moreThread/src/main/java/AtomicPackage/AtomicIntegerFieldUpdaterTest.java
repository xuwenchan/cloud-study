package AtomicPackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import javax.annotation.Generated;

/**
 * 
 * @author 徐文产
 *	AtomicIntegerFieldUpdater类使用
 *
 *
 */
public class AtomicIntegerFieldUpdaterTest {

	/**
	 * 使用AtomicIntegerFieldUpdater传入的字段必须用volatile修饰
	 */
	private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterTest> update=AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTest.class, "count");
	
	public volatile int count=100;//必须用volatile修饰
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	private static AtomicIntegerFieldUpdaterTest atomicIntegerTest=new AtomicIntegerFieldUpdaterTest();
	
	public static void main(String[] args) {
		if(update.compareAndSet(atomicIntegerTest, 100, 120)) {
			System.out.println(atomicIntegerTest.getCount());
		}
		
		if(update.compareAndSet(atomicIntegerTest, 100, 120)) {
			System.out.println(atomicIntegerTest.getCount());
		}else {
			System.out.println("----------"+atomicIntegerTest.getCount());
		}
	}
}

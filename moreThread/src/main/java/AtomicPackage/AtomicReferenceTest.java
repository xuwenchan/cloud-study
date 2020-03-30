package AtomicPackage;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference类的使用
 * @author 徐文产
 *
 */
public class AtomicReferenceTest {

	private static AtomicReference<Integer> count=new AtomicReference(0);
	
	
	
	public static AtomicReference<Integer> getCount() {
		return count;
	}

	public static void setCount(AtomicReference<Integer> count) {
		AtomicReferenceTest.count = count;
	}

	public static void main(String[] args) {
		/**
		 * count.compareAndSet(0,2);
		 * 值为0是将值更新为2
		 */
		count.compareAndSet(0,2);//执行完count为2
		count.compareAndSet(0,1);//count为2，此时不执行
		count.compareAndSet(1,3);//count为2，此时不执行
		count.compareAndSet(2,4);//执行完count 为4
		count.compareAndSet(3,5);//count为4，此时不执行
		System.out.println(count.get());//4
	}
}

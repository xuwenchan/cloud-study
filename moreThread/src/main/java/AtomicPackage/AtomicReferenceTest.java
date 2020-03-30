package AtomicPackage;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference���ʹ��
 * @author ���Ĳ�
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
		 * ֵΪ0�ǽ�ֵ����Ϊ2
		 */
		count.compareAndSet(0,2);//ִ����countΪ2
		count.compareAndSet(0,1);//countΪ2����ʱ��ִ��
		count.compareAndSet(1,3);//countΪ2����ʱ��ִ��
		count.compareAndSet(2,4);//ִ����count Ϊ4
		count.compareAndSet(3,5);//countΪ4����ʱ��ִ��
		System.out.println(count.get());//4
	}
}

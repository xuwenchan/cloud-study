package LockPackage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Synchronized ��ʹ��
 * @author ���Ĳ�
 *	
 *ԭ����-synchronized
 *���δ���飺�������������Ĵ��룬�����ڵ��õĶ���
 *��ͬ����֮�以��Ӱ��	
 *
 *�޸ķ��������������������ڵ��ö���
 *��ͬ����֮�以��Ӱ��
 *
 *
 *
 *���ξ�̬������������̬���������������ж���
 *
 *�޸��ࣺ�����������Ĳ��֣����������ж���
 *
 *��ע����ǣ�
 *��һ������̳��˸��࣬�����˸����ͬ��������
 *�ǼӲ���synchronized��
 *
 *
 *
 */
public class SynchronizedTest {

	//���δ����
	public void test1() {
		synchronized(this) {
			for(int i=0;i<10;i++) {
				System.out.println("---"+i);
			}
		}
	}
	//����һ������
	
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

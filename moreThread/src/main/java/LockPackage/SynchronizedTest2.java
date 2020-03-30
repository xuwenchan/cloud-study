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
 * synchronized:�����ж������ʺϾ��������ң��ɶ��Ժ�
 * 
 * 
 * lock�����ж�����������ͬ������������ʱ��ά�ֳ�̬
 *
 *
 */
public class SynchronizedTest2 {

	//���δ����
	public static void test1() throws InterruptedException {
		synchronized(SynchronizedTest2.class) {
			for(int i=0;i<10;i++) {
				Thread.sleep(10);
				System.out.println("---"+i);
			}
		}
	}
	//����һ������
	
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

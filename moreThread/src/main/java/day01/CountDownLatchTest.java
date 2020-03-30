package day01;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import annoation.NotThreadSafe;

/**
 * ����ģ��
 * @author ���Ĳ�
 *
 */
@NotThreadSafe
public class CountDownLatchTest {

	public static int clientTotal=1000;
	public static int threadTotal=50;
	public static int count=0;
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorServicee=Executors.newCachedThreadPool();
		//��������
		final Semaphore semaphore=new Semaphore(threadTotal);
		final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);
		for(int i=0;i<clientTotal;i++) {
			executorServicee.execute(()->{
				try {
					semaphore.acquire();
					add();
					semaphore.release();
				}catch(Exception e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorServicee.shutdown();
		System.out.println("count:"+count);
	}
	
	public static void add() {
		count++;
	}
	
	/**
	 * �̰߳�ȫ��
	 * ���壺������̷߳���ĳ����ʱ���������л������ú��ֵ��ȷ�ʽ������Щ������ν���ִ�У�
	 * ���������������в���Ҫ�����ͬ������Эͬ������඼�ܱ��ֳ���ȷ����Ϊ����ô�ͳ��������
	 * �̰߳�ȫ��
	 * 
	 * �̰߳�ȫ����Ҫ��������������
	 * ԭ���ԣ��ṩ�˻�����ʣ�ͬһʱ��ֻ����һ���߳����������в���
	 * �ɼ��ԣ�һ���̶߳����ڴ��޸Ŀ��Լ�ʱ�ı������̹߳۲쵽
	 * �����ԣ�һ���̹߳۲������߳��е�ָ��ִ��˳������ָ��������Ĵ��ڣ��ù۲���һ����������
	 * 
	 * 
	 * ԭ���ԣ�
	 * 
	 * �����ԣ�
	 * java�ڴ�ģ���У�����������ʹ�������ָ����������򣬵�����������̲���Ӱ�쵽���̳߳����ִ�У�ȴ��Ӱ�쵽
	 * ���̲߳���ִ�е���ȷ��
	 * 
	 * ������-happens-before 8��ԭ��
	 * 
	 * ����������һ���߳��ڣ����մ���˳����д��ǰ��Ĳ�����ִ�з�������д�ں���Ĳ���
	 * ������Ա�֤�����ڵ��̳߳���ִ�е���ȷ�ԣ��޷���֤�����ڶ��߳��г���ִ�е���ȷ��
	 * 
	 * 
	 * ��������һ��unlock�������з����ں����ͬһ������lock����
	 * 
	 * volatile�������򣺶�һ��������д�������з����ں������������Ķ�����
	 * 
	 * ���ݹ����������A���з����ڲ���B,������B�����з����ڲ���C,����Եó�����A���з����ڲ���C
	 * 
	 * �߳���������:Thread�����start�����������з����ڴ��̵߳�ÿһ������
	 * 
	 * �߳��жϹ��򣺶��߳�interrupt���������ĵ������з����ڱ��ж��̵߳Ĵ����⵽�ж��¼��ķ���
	 * 
	 * �߳��ս�����߳������еĲ��������з������̵߳���ֹ��⣬���ǿ���ͨ��Thread.join��������������
	 * Thread.isAlive()�ķ���ֵ�ֶμ�⵽�߳��Ѿ���ִֹ��
	 * 
	 * �����ս����:һ������ĳ�ʼ��������з���������finalize���������Ŀ�ʼ
	 * 
	 * �ɼ��ԣ�
	 * ���¹���������̼߳䲻�ɼ���ԭ��
	 * 1,�߳̽���ִ��
	 * 2�����������߳̽���ִ��
	 * 3������������º��ֵû���ڹ����ڴ�������估ʱ�ĸ���
	 * �ɼ���-synchronized
	 * JMM����synchronized�������涨
	 * 1���߳̽���ǰ������ѹ������������ֵˢ�����ڴ�
	 * 2���̼߳���ʱ������չ����ڴ��й��������ֵ���Ӷ�ʹ�ù������ʱ��Ҫ�����ڴ������»�ȡ��ȡ���µ�ֵ
	 * ��ע�⣬�����������ͬһ������
	 * �ɼ���-volatile
	 * ͨ�������ڴ����Ϻͽ�ֹ�������Ż���ʵ��
	 * 1����volatile����д����ʱ������д���������һ��store����ָ�
	 * �������ڴ��еĹ������ֵˢ�µ����ڴ�
	 * 2����volatile����������ʱ�����ڶ�����ǰ����һ��load����ָ�
	 * �����ڴ��ж�ȡ�������
	 * 
	 * volatile������ԭ����
	 * 
	 * 
	 * ��������
	 * ��������ʹһ�������ܹ�����ǰ��Χ֮��Ĵ�����ʹ��
	 * 
	 * �����ݳ���һ�ִ���ķ�������һ������û�й������ʱ��
	 * ��ʹ���������߳�����
	 * 
	 * ��ȫ��������
	 * 1���ھ�̬��ʼ�������� ��ʼ��һ����������
	 * 2������������ñ��浽volatile���������AtomicReference������
	 * 3������������ñ��浽ĳ����ȷ��������final��������
	 * 4������������ñ��浽һ����������������
	 * 
	 * ���ɱ����
	 * ���ɱ�����������������
	 * 1�����󴴽��Ժ���״̬�Ͳ����޸�
	 * 2��������������final����
	 * 3����������ȷ�������ڶ��󴴽��ڼ䣬this����û���ݳ���
	 * 
	 * 
	 * final���λ������������ڵ�һ�γ�ʼ�����֮�󲻿ɸ���
	 * final�޸������������ͣ������ʼ�����֮����ָ���µĶ���
	 *
	 * 
	 * �̷߳�գ�
	 * 1��ad-hoc�̷߳�գ��������ʵ�֣�����⣬����
	 * 2����ջ��գ��ֲ��������޲�������
	 * �����÷���ʱ���ֲ�����Ϊ���������̵߳�ջ�У��ǲ��ᱻ�߳�������ģ����в������̰߳�ȫ����
	 * Ϊʲô����ƽʱʹ�õ�SSM���������һ����Ŀʱ�������в��������أ�
	 * ����Ϊ���Ƕ���ͨ������ĳ��������ʵ�ֵģ�����Ǳ��Ⲣ������򵥵İ취�����þֲ���������ʽ
	 * 3��ThreadLocal�̷߳�գ��ر�õķ�շ���
	 * ThreadLocal�ڲ�ʹ��һ��map��ά�ֵģ�key�����̵߳����ƣ�value�������Ƿ�յĶ���
	 * 
	 * 
	 * �̰߳�ȫ-��������J.U.C(java.util.concurren��)
	 * ArrayList--�̰߳�ȫCopyOnWriteArrayList
	 * 
	 * CopyOnWriteArrayListȱ��
	 * ��Ҫ�����µ����飬�����ڴ棬ʵʱ�Բ��ã��ʺ����ڶ���д�ٵĳ���������������һ����
	 * 
	 * HashSet-�̰߳�ȫCopyOnWriteArraySet
	 * 
	 * TreeSet-�̰߳�ȫConcurrentSkipListSet
	 * 
	 * HashMap-�̰߳�ȫConcurrentHashMap��������nullֵ���Զ����������Ż�
	 * 
	 * TreeMap-�̰߳�ȫConcurrentSkipListMap
	 * 
	 * J.U.C�ĺ���AQS(AbstractQueuedSynchronizer)
	 * 
	 * �ײ����ݽṹ��˫���б�
	 * 
	 * ʹ��Nodeʵ��FIFO���У��������ڹ�������������ͬ��װ�õĻ������
	 * 
	 * ������һ��int���ͱ�ʾ״̬
	 * 
	 * 
	 * ʹ�÷����Ǽ̳�
	 * 
	 * ����ͨ���̳в�ͨ��ʵ�����ķ���������״̬{acquire��release}�ķ�������״̬
	 * 
	 * ����ͬʱʵ���������͹�����ģʽ����ռ������
	 * 
	 * AQSͬ�����
	 * CountDownLatch��������ͨ��һ�������ж��߳��Ƿ�Ҫһֱ����
	 * Semaphore������ͬһʱ�䲢���̵߳���Ŀ
	 * ReentrantLock:
	 * Condition:
	 * FutureTask:
	 * 
	 * 
	 */
}

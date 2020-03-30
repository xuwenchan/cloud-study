package day01;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import annoation.NotThreadSafe;

/**
 * 并发模拟
 * @author 徐文产
 *
 */
@NotThreadSafe
public class CountDownLatchTest {

	public static int clientTotal=1000;
	public static int threadTotal=50;
	public static int count=0;
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorServicee=Executors.newCachedThreadPool();
		//允许并发数
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
	 * 线程安全性
	 * 定义：当多个线程访问某个类时，不管运行环境采用何种调度方式或者这些进程如何交替执行，
	 * 并且在主调代码中不需要额外的同步或者协同，这个类都能表现出正确的行为，那么就称这个类是
	 * 线程安全的
	 * 
	 * 线程安全性主要体现在三个方面
	 * 原子性：提供了互斥访问，同一时刻只能有一个线程来对它进行操作
	 * 可见性：一个线程对主内存修改可以及时的被其他线程观察到
	 * 有序性：一个线程观察其他线程中的指令执行顺序，由于指令重排序的存在，该观察结果一般杂乱无序
	 * 
	 * 
	 * 原子性：
	 * 
	 * 有序性：
	 * java内存模型中，允许编译器和处理器对指令进行重排序，但是重排序过程不会影响到单线程程序的执行，却会影响到
	 * 多线程并发执行的正确性
	 * 
	 * 有序性-happens-before 8个原则
	 * 
	 * 程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先执行发生于书写在后面的操作
	 * 这个可以保证程序在单线程程序执行的正确性，无法保证程序在多线程中程序执行的正确性
	 * 
	 * 
	 * 锁定规则：一个unlock操作先行发生于后面对同一个锁的lock操作
	 * 
	 * volatile变量规则：对一个变量的写操作先行发生于后面对这个变量的读操作
	 * 
	 * 传递规则：如果操作A先行发生于操作B,而操作B又先行发生于操作C,则可以得出操作A先行发生于操作C
	 * 
	 * 线程启动规则:Thread对象的start（）方法先行发生于此线程的每一个动作
	 * 
	 * 线程中断规则：对线程interrupt（）方法的调用先行发生于被中断线程的代码检测到中断事件的发生
	 * 
	 * 线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过Thread.join（）方法结束，
	 * Thread.isAlive()的返回值手段检测到线程已经终止执行
	 * 
	 * 对象终结规则:一个对象的初始化完成先行发生于他的finalize（）方法的开始
	 * 
	 * 可见性：
	 * 导致共享变量在线程间不可见的原因
	 * 1,线程交叉执行
	 * 2，重排序结合线程交叉执行
	 * 3，共享变量更新后的值没有在工作内存与主存间及时的更新
	 * 可见性-synchronized
	 * JMM关于synchronized的两条规定
	 * 1，线程解锁前，必须把共享变量的最新值刷到主内存
	 * 2，线程加锁时，将清空工作内存中共享变量的值，从而使用共享变量时需要从主内存中重新获取读取最新的值
	 * （注意，加锁与解锁是同一把锁）
	 * 可见性-volatile
	 * 通过加入内存屏障和禁止重排序优化来实现
	 * 1，对volatile变量写操作时，会在写操作后加入一条store屏障指令，
	 * 将本地内存中的共享变量值刷新到主内存
	 * 2，对volatile变量读操作时，会在读操作前加入一条load屏障指令，
	 * 从主内存中读取共享变量
	 * 
	 * volatile不具有原子性
	 * 
	 * 
	 * 发布对象：
	 * 发布对象：使一个对象能够被当前范围之外的代码所使用
	 * 
	 * 对象逸出：一种错误的发布。当一个对象还没有构造完成时，
	 * 就使它被其他线程所见
	 * 
	 * 安全发布对象
	 * 1，在静态初始化函数中 初始化一个对象引用
	 * 2，将对象的引用保存到volatile类型域或者AtomicReference对象中
	 * 3，将对象的引用保存到某个正确构造对象的final类型域中
	 * 4，将对象的引用保存到一个由锁保护的域中
	 * 
	 * 不可变对象：
	 * 不可变对象需满足的条件：
	 * 1，对象创建以后其状态就不能修改
	 * 2，对象所有域都是final类型
	 * 3，对象是正确创建（在对象创建期间，this引用没有逸出）
	 * 
	 * 
	 * final修饰基本数据类型在第一次初始化完成之后不可更改
	 * final修改引用数据类型，对象初始换完成之后不能指向新的对象
	 *
	 * 
	 * 线程封闭：
	 * 1，ad-hoc线程封闭：程序控制实现，最糟糕，忽略
	 * 2，堆栈封闭：局部变量，无并发问题
	 * 当调用方法时，局部变量为被拷贝到线程的栈中，是不会被线程所共享的，所有不会有线程安全问题
	 * 为什么我们平时使用的SSM框架来开发一个项目时，很少有并发问题呢，
	 * 是因为我们都是通过调用某个方法来实现的，这个是避免并发的最简单的办法，采用局部变量的形式
	 * 3，ThreadLocal线程封闭：特别好的封闭方法
	 * ThreadLocal内部使用一个map来维持的，key就是线程的名称，value就是我们封闭的对象
	 * 
	 * 
	 * 线程安全-并发容器J.U.C(java.util.concurren包)
	 * ArrayList--线程安全CopyOnWriteArrayList
	 * 
	 * CopyOnWriteArrayList缺点
	 * 需要开辟新的数组，消耗内存，实时性不好，适合用在读多写少的场景，能做到最终一致性
	 * 
	 * HashSet-线程安全CopyOnWriteArraySet
	 * 
	 * TreeSet-线程安全ConcurrentSkipListSet
	 * 
	 * HashMap-线程安全ConcurrentHashMap，不允许null值，对读操作进行优化
	 * 
	 * TreeMap-线程安全ConcurrentSkipListMap
	 * 
	 * J.U.C的核心AQS(AbstractQueuedSynchronizer)
	 * 
	 * 底层数据结构：双向列表
	 * 
	 * 使用Node实现FIFO队列，可以用于构建锁或者其他同步装置的基础框架
	 * 
	 * 利用了一个int类型表示状态
	 * 
	 * 
	 * 使用方法是继承
	 * 
	 * 子类通过继承并通过实现它的方法管理其状态{acquire和release}的方法操作状态
	 * 
	 * 可以同时实现排它锁和共享锁模式（独占、共享）
	 * 
	 * AQS同步组件
	 * CountDownLatch：闭锁，通过一个计数判断线程是否要一直阻塞
	 * Semaphore：控制同一时间并发线程的数目
	 * ReentrantLock:
	 * Condition:
	 * FutureTask:
	 * 
	 * 
	 */
}

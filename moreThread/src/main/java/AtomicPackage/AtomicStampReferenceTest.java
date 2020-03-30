package AtomicPackage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 介绍AtomicStampReference类的使用
 * 这个类是解决CAS的ABA问题
 * 
 * 什么是CAS的ABA问题：
 * 在多线程环境中，一个线程将一个变量从A值改为B值，但又将B值改为A值，
 * 另一个线程去和该变量的值比较时，发现值没有变，又将A值改为其他值，
 * 但此时该变量的值已经被其他线程更改，只是又改回来了而已
 * 
 * 解决思路：是给该变量加上一个版本号，例如：一个线程将值改为A,version为1，将A改为B,version2，又将B值改为A,version为3
 * 
 * @author 徐文产
 *
 */
public class AtomicStampReferenceTest {

	 private static AtomicInteger atomicInt = new AtomicInteger(100);
     private static AtomicStampedReference atomicStampedRef = new AtomicStampedReference(100, 0);

     public static void main(String[] args) throws InterruptedException {
             Thread intT1 = new Thread(new Runnable() {
                     @Override
                     public void run() {
                             atomicInt.compareAndSet(100, 101);
                             atomicInt.compareAndSet(101, 100);
                     }
             });

             Thread intT2 = new Thread(new Runnable() {
                     @Override
                     public void run() {
                             try {
                                     TimeUnit.SECONDS.sleep(1);
                             } catch (InterruptedException e) {
                             }
                             boolean c3 = atomicInt.compareAndSet(100, 101);
                             System.out.println(c3); // true
                     }
             });

             intT1.start();
             intT2.start();
             intT1.join();
             intT2.join();

             Thread refT1 = new Thread(new Runnable() {
                     @Override
                     public void run() {
                             try {
                                     TimeUnit.SECONDS.sleep(1);
                             } catch (InterruptedException e) {
                             }
                             atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
                             atomicStampedRef.compareAndSet(101, 100, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
                     }
             });

             Thread refT2 = new Thread(new Runnable() {
                     @Override
                     public void run() {
                             int stamp = atomicStampedRef.getStamp();
                             try {
                                     TimeUnit.SECONDS.sleep(2);
                             } catch (InterruptedException e) {
                             }
                             boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
                             System.out.println(c3); // false
                     }
             });

             refT1.start();
             refT2.start();
     }
}

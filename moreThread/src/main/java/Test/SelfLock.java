package Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SelfLock implements Lock {
    private final Sync sync=new Sync(1);

    @Override
    public void lock() {
        sync.tryAcquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.tryRelease(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private static final class Sync extends AbstractQueuedSynchronizer{
        Sync(int count){
            if(count<0){
                throw new IllegalArgumentException("count must larges zero");
            }
            setState(count);
        }

        public int tryAcquire(int reductCount){
            for(;;){
                int current=getState();
                int newCount=current-reductCount;
                if(newCount<0|| compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }


        public boolean tryRelease(int returnCount){
            for(;;){
                int current=getState();
                int newCount=current+returnCount;
                if(compareAndSetState(current,newCount)){
                    return true;
                }
            }
        }
    }

}

package LockPackage;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.crypto.Data;

public class ReentrantReadWriteLockTest {

	private final static Map<String,String> map=new TreeMap();
	
	private final static ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
	
	private final Lock readLock=lock.readLock();
	
	private final Lock writeLock=lock.writeLock();
	
	public String get(String key) {
		readLock.lock();
		try {
			return map.get(key);
		}finally {
			readLock.unlock();
		}
		
	}
	
	public Set<String> getAllkeys(){
		readLock.lock();
		try {
			return map.keySet();
		}finally {
			readLock.unlock();
		}
	}
	
	public void put(String key,String value) {
		writeLock.lock();
		try {
			 map.put(key, value);
		}finally {
			writeLock.unlock();
		}
	}
	
	
	public static void main(String[] args) {
		
	}
}

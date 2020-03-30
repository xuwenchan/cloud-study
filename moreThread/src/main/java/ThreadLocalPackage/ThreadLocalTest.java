package ThreadLocalPackage;

public class ThreadLocalTest {

	private final static ThreadLocal<Long> requestHolder=new ThreadLocal<Long>();
	
	
	/**
	 * 什么时候使用，
	 * 请求进入后端服务器，但还没有实际的处理的时候
	 * @param id
	 */
	public static void add(Long id) {
		requestHolder.set(id);
	}
	
	public static Long getId() {
		return requestHolder.get();
	}
	
	/**
	 * 接口实际处理完之后调用此方法
	 */
	public static void remove() {
		requestHolder.remove();
	}
	
	public static void main(String[] args) {
		
	}
}

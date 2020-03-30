package ThreadLocalPackage;

public class ThreadLocalTest {

	private final static ThreadLocal<Long> requestHolder=new ThreadLocal<Long>();
	
	
	/**
	 * ʲôʱ��ʹ�ã�
	 * ��������˷�����������û��ʵ�ʵĴ����ʱ��
	 * @param id
	 */
	public static void add(Long id) {
		requestHolder.set(id);
	}
	
	public static Long getId() {
		return requestHolder.get();
	}
	
	/**
	 * �ӿ�ʵ�ʴ�����֮����ô˷���
	 */
	public static void remove() {
		requestHolder.remove();
	}
	
	public static void main(String[] args) {
		
	}
}

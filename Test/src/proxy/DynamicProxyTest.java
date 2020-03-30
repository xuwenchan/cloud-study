package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * ��̬���� -- JDK
 * @author Administrator
 */
public class DynamicProxyTest {
	public static void main(String[] args) {
		// ����Ҫ�������ʵ����
		Jiaoyi1 fangNu = new FangNu();
		// ����Ҫ�����ĸ���ʵ���󣬾ͽ��ö��󴫽�ȥ�������ͨ������ʵ�����������䷽����
		InvocationHandler proxy = new ZhongJieProxy2(fangNu);
		
		 /*
         * ͨ��Proxy��newProxyInstance�������������ǵĴ����������������
         * ��һ������ �����������ڶ���������������� ��handler.getClass().getClassLoader() ����������ʹ��handler������ClassLoader�������������ǵĴ������
         * �ڶ��������� ������Ķ������Ľӿ��б�ʵ�� ��realSubject.getClass().getInterfaces()����������Ϊ��������ṩ�Ľӿ�����ʵ������ʵ�еĽӿڣ���ʾ��Ҫ������Ǹ���ʵ���������Ҿ��ܵ�������ӿ��еķ�����
         * ������������ ���õ��ô�����򣬽��������÷��ɸ��Ĵ������ handler�� �������ｫ������������������Ϸ��� InvocationHandler ���������
         */
		Jiaoyi1 jiaoYiYuan = (Jiaoyi1) Proxy.newProxyInstance(proxy.getClass().getClassLoader(),
				fangNu.getClass().getInterfaces(), proxy);
		jiaoYiYuan.maifang("����С��", 1500000);
		System.out.println("-----------------------------");
	}
}


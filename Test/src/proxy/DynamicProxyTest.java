package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理 -- JDK
 * @author Administrator
 */
public class DynamicProxyTest {
	public static void main(String[] args) {
		// 我们要代理的真实对象
		Jiaoyi1 fangNu = new FangNu();
		// 我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
		InvocationHandler proxy = new ZhongJieProxy2(fangNu);
		
		 /*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，三个参数：
         * 第一个参数 ：加载器用于定义代理类的类加载器 ，handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数： 被代理的对象的类的接口列表实现 ，realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数： 设置调用处理程序，将方法调用分派给的代理对象， handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
		Jiaoyi1 jiaoYiYuan = (Jiaoyi1) Proxy.newProxyInstance(proxy.getClass().getClassLoader(),
				fangNu.getClass().getInterfaces(), proxy);
		jiaoYiYuan.maifang("上善小区", 1500000);
		System.out.println("-----------------------------");
	}
}


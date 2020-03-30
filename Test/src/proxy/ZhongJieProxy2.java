package proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
	 * 中介 -- 代理 -- 1
	 * @author Administrator
	 */
public	class ZhongJieProxy2 implements InvocationHandler {
		Jiaoyi1 fangNu;
	
		public ZhongJieProxy2(Jiaoyi1 fangNu) {
			this.fangNu = fangNu;
		}
	
	//	每一个动态代理类都必须要实现InvocationHandler这个接口，并且每个代理类的实例都关联到了一个handler，
	//	当我们通过代理对象调用一个方法的时候，这个方法的调用就会被转发为由InvocationHandler这个接口的 invoke 方法来进行调用。
	//	InvocationHandler这个接口的唯一一个方法 invoke 方法：
	//	该方法接收3个参数：
	//	proxy:　 指代我们所代理的那个真实对象
	//	method: 指代的是我们所要调用真实对象的某个方法的Method对象
	//	args:　　指代的是调用真实对象某个方法时接受的参数
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// 在代理真实对象前我们可以添加一些自己的操作
			System.out.println("静态代理前置内容 >> 中介挑选房子 ... ");
			System.out.println("Method:" + method);
	
			// 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
			Object obj = method.invoke(fangNu, args); 
			System.out.println("方法参数:"+ Arrays.toString(args));
	
			// 在代理真实对象后我们也可以添加一些自己的操作
			System.out.println("静态代理后置内容 >> 中介收取中介费，办手续交房 ... ");
			return obj;
		}
	}

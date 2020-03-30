package proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
	 * �н� -- ���� -- 1
	 * @author Administrator
	 */
public	class ZhongJieProxy2 implements InvocationHandler {
		Jiaoyi1 fangNu;
	
		public ZhongJieProxy2(Jiaoyi1 fangNu) {
			this.fangNu = fangNu;
		}
	
	//	ÿһ����̬�����඼����Ҫʵ��InvocationHandler����ӿڣ�����ÿ���������ʵ������������һ��handler��
	//	������ͨ������������һ��������ʱ����������ĵ��þͻᱻת��Ϊ��InvocationHandler����ӿڵ� invoke ���������е��á�
	//	InvocationHandler����ӿڵ�Ψһһ������ invoke ������
	//	�÷�������3��������
	//	proxy:�� ָ��������������Ǹ���ʵ����
	//	method: ָ������������Ҫ������ʵ�����ĳ��������Method����
	//	args:����ָ�����ǵ�����ʵ����ĳ������ʱ���ܵĲ���
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// �ڴ�����ʵ����ǰ���ǿ������һЩ�Լ��Ĳ���
			System.out.println("��̬����ǰ������ >> �н���ѡ���� ... ");
			System.out.println("Method:" + method);
	
			// ��������������ʵ����ķ���ʱ������Զ�����ת��������������handler�����invoke���������е���
			Object obj = method.invoke(fangNu, args); 
			System.out.println("��������:"+ Arrays.toString(args));
	
			// �ڴ�����ʵ���������Ҳ�������һЩ�Լ��Ĳ���
			System.out.println("��̬����������� >> �н���ȡ�н�ѣ����������� ... ");
			return obj;
		}
	}

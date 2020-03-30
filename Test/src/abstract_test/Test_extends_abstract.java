package abstract_test;

public class Test_extends_abstract extends Test_abstract {

	@Override
	public String getName() {
		System.out.println("调用getName方法");
		return "徐文产";
	}

	@Override
	public int getAge() {
		System.out.println("调用getAge方法");
		return 18;
	}

}

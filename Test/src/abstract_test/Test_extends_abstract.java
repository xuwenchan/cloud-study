package abstract_test;

public class Test_extends_abstract extends Test_abstract {

	@Override
	public String getName() {
		System.out.println("����getName����");
		return "���Ĳ�";
	}

	@Override
	public int getAge() {
		System.out.println("����getAge����");
		return 18;
	}

}

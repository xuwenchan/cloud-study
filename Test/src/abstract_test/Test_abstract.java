package abstract_test;

public abstract class Test_abstract implements Test_interface{

	@Override
	public String getAddress() {
		System.out.println("����getAddress����");
		getName();
		getAge();
		return "����ʡ������";
	}

	@Override
	public abstract String getName();

	@Override
	public abstract int getAge();

	
	
}

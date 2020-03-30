package abstract_test;

public abstract class Test_abstract implements Test_interface{

	@Override
	public String getAddress() {
		System.out.println("调用getAddress方法");
		getName();
		getAge();
		return "河南省郸城县";
	}

	@Override
	public abstract String getName();

	@Override
	public abstract int getAge();

	
	
}

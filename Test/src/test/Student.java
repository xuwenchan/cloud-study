package test;

class Person{
	public int a=0;
	public void find() {
		System.out.println("---------person-----------");
	}
	
}


public class Student extends Person {

	public int a=1;
	
	public void find() {
		System.out.println("---------student-----------");
	}
	
	public static void main(String[] args) {
		Person p=new Student();
		System.out.println(p.a);
		p.find();
	}
	
	
	
}

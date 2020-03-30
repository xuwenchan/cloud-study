package publish;

/**
 * 对象逸出
 * @author 徐文产
 *
 */
public class Escape {
	
	private int thisCanBeEscape=0;
	public Escape() {
		new InnnerClass();
	}
	
	private class InnnerClass{
		public InnnerClass() {
			System.out.println(Escape.this.thisCanBeEscape);
		}
	}
	
	public static void main(String[] args) {
		new Escape();
	}

}

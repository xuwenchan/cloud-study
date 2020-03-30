package publish;

import java.util.Arrays;

public class UnsafePublish {

	private String[] states= {"a","b","c"};
	
	public String[] getStates() {
		return states;
	}
	
	public static void main(String[] args) {
		UnsafePublish unsafsPublish=new UnsafePublish();
		System.out.println(Arrays.toString(unsafsPublish.getStates()));
		
		unsafsPublish.getStates()[0]="d";
		System.out.println(Arrays.toString(unsafsPublish.getStates()));
	}
}

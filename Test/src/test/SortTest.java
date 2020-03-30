package test;

public class SortTest {
	
		public static void main(String[] args) {
			int[] arr= {9,8,761,6,46,1,7,3,1,8,6,17,6,1,73,1,5,3};
			maopao(arr);
			
			
		}
		
		public static void maopao(int[] arr) {
			for(int i=0;i<arr.length-1;i++) {
				for(int j=0;j<arr.length-1-i;j++) {
					if(arr[j]>arr[j+1]) {
						int temp=arr[j];
						arr[j]=arr[j+1];
						arr[j+1]=temp;
					}
				}
			}
			
			for(int k=0;k<arr.length;k++) {
				System.out.print(arr[k]+"\t");
			}
			
			
		}

}

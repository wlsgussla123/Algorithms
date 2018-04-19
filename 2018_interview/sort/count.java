package datastructure;

public class CountingSortMain {
	public static int[] countSort(int[] arr, int max) {
		int len = arr.length;
		int[] result = new int[len];
		int[] count = new int[max+1];
		
		for(int i=0; i<len; i++) {
			count[arr[i]]++;
		}
		
		for(int i=0; i<max; i++) {
			count[i+1] += count[i]; 
		}
		
		for(int i=len-1; i>=0; i--) {
			result[count[arr[i]]-1] = arr[i];
			count[arr[i]]--;
		}
		
		return result;
	}
	
	public static int getMax(int[] arr) {
		int len = arr.length;
		int max = arr[0];
		for(int i=1; i<len; i++) {
			if(max < arr[i]) {
				max = arr[i];
			}
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		int[] arr = {3,4,0,1,2,4,2,4};
		print(countSort(arr, getMax(arr)));
	}
	
	public static void print(int[] arr) {
		int len = arr.length;
		for(int i=0; i<len; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}

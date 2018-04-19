package datastructure;

public class RadixSortMain {
	public static int getMax(int[] arr) {
		int max = arr[0];
		int len = arr.length;
		for(int i=1; i<len; i++) {
			if(max < arr[i]) max = arr[i];
		}
		
		return max;
	}
	
	public static void countSort(int[] arr, int exp) {
		int[] count = new int[10];
		int len = arr.length;
		int[] result = new int[len];
		
		for(int i=0; i<len; i++) {
			count[(arr[i]/exp)%10]++;
		}
		
		for(int i=0; i<9; i++) {
			count[i+1] += count[i];
		}
		
		for(int i=len-1; i>=0; i--) {
			result[count[(arr[i]/exp)%10]-1] = arr[i];
			count[(arr[i]/exp)%10]--;
		}
		
		for(int i=0; i<len; i++) {
			arr[i] = result[i];
		}
	}
	
	public static void radixSort(int[] arr) {
		int max = getMax(arr);
		for(int exp=1; max/exp > 0; exp*=10) {
			countSort(arr, exp);
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {91, 2, 718, 1002, 102};
		radixSort(arr);
		print(arr);
	}
	
	public static void print(int[] arr) {
		int len = arr.length;
		for(int i=0; i<len; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}

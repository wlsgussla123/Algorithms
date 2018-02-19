package algo;

import java.util.Scanner;

public class Main {
	public static final Scanner sc = new Scanner(System.in);
	public static int N;
	public static void main(String[] args) {
		N = sc.nextInt();
		int max = 0;
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
			if(max < arr[i]) max = arr[i];
		}
		
		radixSort(arr, max);
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
	
	public static void radixSort(int[] arr, int max) {
		int d = max;
		for(int exp=1; d/exp > 0; exp*=10) {
			countSort(arr, exp);
			for(int i=0; i<N; i++) System.out.println(arr[i]);
			System.out.println();
		}
	}
}

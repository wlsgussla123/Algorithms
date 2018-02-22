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
		
		arr = countSort(arr, max);
		for(int i=0; i<N; i++) System.out.println(arr[i]);
	}
	
	public static int[] countSort(int[] arr, int max) {
		int[] count = new int[max+1];
		int[] result = new int[N];
		
		for(int i=0; i<N; i++) {
			count[arr[i]]++;
		}
		
		for(int i=1; i<max; i++) {
			count[i+1] += count[i];
		}
		
		for(int i=N-1; i>=0; i--) {
			result[count[arr[i]]-1] = arr[i];
			count[arr[i]]--;
		}
		
		return result;
	}
}

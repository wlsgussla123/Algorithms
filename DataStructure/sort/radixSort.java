package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

class Task {
	private int N;
	private int[] arr;
	
	private final Scanner sc = new Scanner(System.in);
	
	private void input() {
		N = sc.nextInt();
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
	}
	
	private void countSort(int exp) {
		int output[] = new int[N];
		int count[] = new int[10]; // for count
		int i;
		Arrays.fill(count, 0);

		// count
		for(i=0; i<N; i++) {
			count[ (arr[i]/exp)%10 ]++;
		}
		
		// countSum
		for(i=1; i<10; i++) {
			count[i] += count[i-1];
		}
		
		// Build the output array
		for(i=N-1; i>=0; i--) {
			output[count[(arr[i]/exp)%10] -1] = arr[i];
			count[(arr[i]/exp)%10]--;
		}
		
		for(i=0; i<N; i++)
			arr[i] = output[i];
	}
	
	private void radixSort() {
		int m = getMax();
		
		for(int exp=1; m/exp > 0; exp *= 10) {
			countSort(exp);
		}
	}
	
	private int getMax() {
		int max = arr[0];
		for(int i=1; i<N; i++) {
			if(arr[i] > max)
				max = arr[i];
		}
		
		return max;
	}
	
	private void print() {
		for(int i=0; i<N; i++) {
			System.out.println(arr[i]);
		}
	}
	
	public void run() {
		input();
		radixSort();
		print();
	}
}

public class Main {
	public static void main(String[] args) {
		Task task = new Task();
		task.run();
	}
}

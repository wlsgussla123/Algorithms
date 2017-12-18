package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

class Task {
	private int x;
	private int N;
	private int[] count; // count 배열 
	private int[] number; // 숫자들
	private int[] result;
	
	private final Scanner sc = new Scanner(System.in);
	
	private void input() {
		N = sc.nextInt();
		count = new int[N+1];
		number = new int[N+1];
		result = new int[N+1];
		
		for(int i=0; i<N; i++) {
			number[i] = sc.nextInt();
		}
	}
	
	private void countSort() {
		for(int i=0; i<=N; i++) {
			count[i] = 0;
		}
		
		// count
		for(int i=0; i<N; i++) {
			count[number[i]]++;
		}
		
		// 누적합		
		for(int i=1; i<N; i++) {
			count[i] += count[i-1];
		}
		
		for(int i=0; i<N; i++) {
			result[count[number[i]]] = number[i];
			count[number[i]] -= 1;
		}
	}
	
	private void print() {
		for(int i=1; i<=N; i++) {
			System.out.println(result[i]);
		}
	}
	
	public void run() {
		input();
		countSort();
		print();
	}
}

public class Main {
	public static void main(String[] args) {
		Task task = new Task();
		task.run();
	}
}

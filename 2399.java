package algo;

import java.util.Arrays;
import java.util.Scanner;

class Task {
	private int N;
	private long[] x;
	private final Scanner sc = new Scanner(System.in);
	
	private void input() {
		N = sc.nextInt();
		x = new long[N];
		
		for(int i=0; i<N; i++) {
			x[i] = sc.nextLong();
		}
	}
	
	private void offset() {
		long answer = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				answer += Math.abs((x[i] - x[j]));
			}
		}
		
		System.out.println(answer);
	}
	
	public void run() {
		input();
		offset();
	}
}

public class Main {
	public static void main(String[] args) {
		Task task = new Task();
		task.run();
	}
}

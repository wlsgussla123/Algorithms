package algo;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Task task = new Task();
		task.run();
	}
	
	static class Task {
		private static final Scanner sc = new Scanner(System.in);
		private int[] A;
		private int[] B;
		private int N;
		private int answer=0;
		
		public void input() {
			N = sc.nextInt();
			
			A = new int[N];
			for(int i=0; i<N; i++) {
				A[i] = sc.nextInt();
			}
			B = new int[N];
			for(int i=0; i<N; i++) {
				B[i] = sc.nextInt();
			}
		}
		
		public void process() {
			Arrays.sort(A);
			Arrays.sort(B);
			
			for(int i=0; i<N; i++) {
				answer += A[i] * B[N-1-i];
			}
		}
		
		public void run() {
			input();
			process();
			
			System.out.println(answer);
		}
	}
}

package algo;

import java.util.Arrays;
import java.util.Scanner;

class Task {
	private int x;
	private int N;
	private int[] count;
	
	private final Scanner sc = new Scanner(System.in);
	
	private void input() {
		N = sc.nextInt();
		count = new int[N];
		
		for(int i=0; i<N; i++) {
			int x = sc.nextInt(); // 배열에 값 입력
			count[x]++;
		}
	}
	
	private void countSort() {
		for(int i=0; i<N; i++) {
			while(count[i] != 0) {
				--count[i];
				System.out.println(i);
			}
		}
	}
	
	public void run() {
		input();
		countSort();
	}
}

public class Main {
	public static void main(String[] args) {
		Task task = new Task();
		task.run();
	}
}

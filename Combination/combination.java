package algo;

import java.io.IOException;

class Task {
	private int[] a = {1,2,3,4,5};
	private int[] T = new int[5];
	
	// 단순히 경우의 수만 
	private int combinationOfNumber(int n, int r) {
		if(n==r || r==0) return 1;
		else return combinationOfNumber(n-1, r-1) + combinationOfNumber(n-1, r);
	}
	
	private void print(int q) {
		for(int i=q-1; i>=0; i--) {
			System.out.print(T[i]);
		}
		System.out.println();
	}
	
	// 각 과정들까지 출력
	private void combination(int n, int r, int q) {
		if(r == 0) {
			print(q);
		} else if(n<r) {
			return;
		} else {
			T[r-1] = a[n-1];
			combination(n-1, r-1, q);
			combination(n-1, r, q);
		}
	}
	
	public void run() throws IOException {
		System.out.println(combinationOfNumber(5,3));
		combination(3, 2, 2);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

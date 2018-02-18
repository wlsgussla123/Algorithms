package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[] A;
	private int[] B;
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		A = new int[N];
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
		B = new int[M];
		st = getStringTokenizer();
		for(int i=0; i<M; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
	}
	private void process() {
		Arrays.sort(A);
		
		for(int i=0; i<M; i++) {
			int key = B[i];
			int left=0, right=N-1;
			boolean flag = false;
			
			while(left <= right) {
				int mid = (left + right) / 2;
				
				if(A[mid] < key) {
					left = mid+1;
				} else if(A[mid] > key) {
					right = mid-1;
				} else {
					flag = true;
					break;
				}
			}
			
			if(flag) System.out.println("1");
			else System.out.println("0");
		}
	}
	
	public void run() throws IOException {
		input();
		process();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

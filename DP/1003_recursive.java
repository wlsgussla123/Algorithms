package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int T, N;
	private int[] dp;
	private int zeroCnt = 0, oneCnt = 0;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		dp = new int[N+1];
		zeroCnt = 0;
		oneCnt = 0;
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
	}
	
	private int fibonacci(int N) {
		if(N==0) {
			zeroCnt++;
			return 0;
		} else if(N==1) {
			oneCnt++;
			return 1;
		} else {
			return fibonacci(N-2) + fibonacci(N-1);
		}
	}
	
	private void answer() {
		System.out.println(zeroCnt + " " + oneCnt);
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		T = Integer.parseInt(st.nextToken());
		while(T>0) {
			input();
			fibonacci(N);
			answer();
			
			T--;
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

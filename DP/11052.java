package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int[] dp;
	private int[] P;
	private int N;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		P = new int[N+1];
		dp = new int[N+1];
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		
		st = getStringTokenizer();
		for(int i=1; i<=N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private int max(int a, int b) {
		return a > b ? a : b;
	}
	
	private void process() {
		dp[0] = 0;
		dp[1] = P[1];
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=i; j++) {
				dp[i] = max(dp[i], dp[i-j] + P[j]);
			}
		}
		
		System.out.println(dp[N]);
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

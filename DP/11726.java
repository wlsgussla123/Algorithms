package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int[] dp;
	private int N;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		dp = new int[N+3];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
	}
	
	private int process() {
		if(N<=2) {
			return dp[N] % 10007;
		}
		for(int i=3; i<=N; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 10007;
		}
		
		return dp[N];
	}
	
	public void run() throws IOException {
		input();
		System.out.println(process());
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

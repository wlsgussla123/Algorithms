package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int[][] dp;
	private int T, N;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		dp = new int[N+1][2];
		for(int i=0; i<=N; i++) {
			for(int j=0; j<2; j++) {
				dp[i][j] = 0;
			}
		}
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
	}
	
	private void fibonacci(int N) {
		if(N>0) {
			dp[0][0] = 1;
			dp[0][1] = 0;
			dp[1][0] = 0;
			dp[1][1] = 1;
		} else {
			dp[0][0] = 1;
			dp[0][1] = 0;			
		}

		for(int i=2; i<=N; i++) {
			dp[i][0] = dp[i-1][0] + dp[i-2][0];
			dp[i][1] = dp[i-1][1] + dp[i-2][1];
		}
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		T = Integer.parseInt(st.nextToken());
		while(T>0) {
			input();
			fibonacci(N);
			System.out.println(dp[N][0] + " " + dp[N][1]);
			
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

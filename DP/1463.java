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
		dp = new int[N+4];
		dp[0] = 0;
		dp[1] = 0;
		dp[2] = dp[3] = 1;
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
	}
	
	private int process() {
		if(N<=3) {
			return dp[N];
		}
		
		for(int i=4; i<=N; i++) {
			dp[i] = dp[i-1] + 1;
			if(i%2 == 0 && dp[i] > dp[i/2] + 1) {
				dp[i] = dp[i/2] + 1;
			}
			if(i%3 == 0 && dp[i] > dp[i/3] + 1) {
				dp[i] = dp[i/3] + 1;
			}
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

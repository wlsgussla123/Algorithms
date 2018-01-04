package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Task {
	private int N;
	private int[] cost;
	private long[][] dp;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		cost = new int[N+1];
		dp = new long[N+1][3];
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			cost[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	// 1000, 900, 2, 1, 800, 700인 경우.. 두 번을 안 마셔야 할 수도 있다.
	private long max(long a, long b, long c) {
		long value = a > b ? a : b;
		return value > c ? value : c;
	}
	
	private long max(long a, long b) {
		return a > b ? a : b;
	}
	
	private void process() {
		dp[1][0] = 0;
		dp[1][1] = cost[1];
		dp[1][2] = cost[1];
		
		for(int i=2; i<=N; i++) {
			for(int j=0; j<=2; j++) {
				if(j==0) {
					dp[i][j] = max(dp[i-1][0], dp[i-1][1], dp[i-1][2]);
				} else if(j==1) {
					dp[i][j] = cost[i] + dp[i-1][0];
				} else {
					dp[i][j] = cost[i] + dp[i-1][1];
				}
			}
		}
	}
	
	private void getAnswer() {
		long answer = 0;
		for(int i=0; i<=2; i++) {
			answer = max(answer, dp[N][i]);
		}
		System.out.println(answer);
	}
	
	public void run() throws IOException {
		input();
		process();
		getAnswer();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

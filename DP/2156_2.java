package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] grape;
	private long[] dp;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		dp = new long[N+3];
		grape = new int[N+3];
	}

	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			grape[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private long max(long a, long b, long c) {
		long temp = a > b ? a : b;
		return temp > c ? temp : c;
	}
	
	private void process() {
		dp[1] = grape[1];
		dp[2] = dp[1] + grape[2];			
		
		for(int i=3; i<=N; i++) {
			// 안 마셨을 때, 한 잔 연속, 두 잔 연속
			dp[i] = max(dp[i-1], dp[i-2] + grape[i], dp[i-3] + grape[i-1] + grape[i]);
		}
		
		System.out.println(dp[N]);
	}

	public void run() throws IOException {
		input();
		process();
		br.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

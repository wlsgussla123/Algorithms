package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] floor;
	private long[] dp;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		dp = new long[N+3];
		floor = new int[N+3];
	}

	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			floor[i] = Integer.parseInt(st.nextToken());
		}
	}

	private long max(long a, long b) {
		return a > b ? a : b;
	}

	private void process() {
		dp[1] = floor[1];
		dp[2] = floor[1] + floor[2];
		
		for(int i=3; i<=N; i++) {
			dp[i] = max(dp[i-3] + floor[i-1] + floor[i], dp[i-2] + floor[i]);
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

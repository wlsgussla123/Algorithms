package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Task {
	private int N;
	private long[][] dp;
	private final long mod = 1000000000;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		dp = new long[N+1][10];
	}

	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
	}
	
	private void process() {
		for(int i=1; i<=9; i++) {
			dp[1][i] = 1;
		}
		
		for(int i=2; i<=N; i++) {
			for(int j=0; j<=9; j++) {
				if(j==0) {
					dp[i][j] = dp[i-1][j+1] % mod;
				} else if(j==9) {
					dp[i][j] = dp[i-1][j-1] % mod;
				} else {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1] % mod;
				}
			}
		}
		
		long answer = 0;
		for(int i=0; i<=9; i++) {
			answer = (answer + dp[N][i]) % mod;
		}
		
		System.out.println(answer);
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

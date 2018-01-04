package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N,M,T;
	private long[][] dp;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		dp = new long[N+1][M+1];
		for(int i=0; i<=M; i++) {
			dp[1][i] = i;
		}
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
	}
	
	private void process() {
		for(int i=2; i<=N; i++) {
			for(int j=i; j<=M; j++) {
				for(int k=j; k>=i; k--) {
					dp[i][j] += dp[i-1][k-1];
				}
			}
		}
		System.out.println(dp[N][M]);
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		T = Integer.parseInt(st.nextToken());
		
		while(T-->0) {
			input();
			process();	
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

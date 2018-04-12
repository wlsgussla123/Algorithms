package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N;
		private int[] dp;
		private final int INF = 987654321;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			dp = new int[N+1];
			for(int i=0; i<=N; i++) {
				dp[i] = INF;
			}
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			init();
		}
		
		private int dfs(int n) {
			if(n==1) return 0;
			if(dp[n] != INF) {
				return dp[n];
			}
			
			int temp = INF;
			if(n%3 == 0 && n/3>0) {
				temp = dfs(n/3) + 1;
			}
			
			if(n%2 == 0 && n/2>0) {
				temp = Math.min(temp, dfs(n/2) + 1);
			}
			
			return dp[n] = Math.min(temp, dfs(n-1) + 1);
 		}
		
		public void run() throws IOException {
			input();
			bw.write(String.valueOf(dfs(N)));
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

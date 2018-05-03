package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N;
		private int[][] dp;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			dp = new int[N+1][3];
		}
		
		private void solve(int N) throws IOException {
			dp[1][0] = 1;
			dp[1][1] = 1;
			dp[1][2] = 1;
			
			for(int i=2; i<=N; i++) {
				dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
				dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;
				dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901;
			}
			
			bw.write(String.valueOf((dp[N][0] + dp[N][1] + dp[N][2]) % 9901));
		}
		
		public void run() throws IOException {
			input();
			solve(N);
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

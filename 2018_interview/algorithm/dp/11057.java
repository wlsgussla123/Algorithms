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
		private int N,M;
		private int[][] dp;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			dp = new int[1005][10];
			for(int i=0; i<=9; i++) {
				dp[1][i] = 1;
			}
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			init();
		}
		
		private void solve() throws IOException {
			for(int i=2; i<=N; i++) {
				for(int j=0; j<=9; j++) {
					for(int k=0; k<=j; k++) {
						dp[i][j] += dp[i-1][k];
					}
					dp[i][j] = dp[i][j] % 10007;
				}
			}
			
			int sum = 0;
			for(int i=0; i<=9; i++) {
				sum += dp[N][i];
			}
			bw.write(String.valueOf(sum % 10007));
		}
		
		public void run() throws IOException {
			input();
			solve();
			close();
		}
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

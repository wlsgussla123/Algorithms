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
		private int N,K;
		private int[][] dp = new int[1001][1001];
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					dp[i][j] = -1;
				}
			}
		}
		
		private int bino(int n, int k) {
			if(k==0 || k==n) {
				return 1;
			}
			
			if(dp[n][k] != -1) {
				return dp[n][k];
			}
			
			return dp[n][k] = (bino(n-1, k-1) + bino(n-1, k)) % 10007;
		}
		
		public void run() throws IOException {
			input();
			bw.write(String.valueOf(bino(N, K)));
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

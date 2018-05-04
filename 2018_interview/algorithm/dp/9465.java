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
		private int[][] arr;
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
			arr = new int[3][N+5];
			dp = new int[3][N+5];
			
			for(int i=1; i<=2; i++) {
				st = getStringTokenizer();
				for(int j=1; j<=N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		private void solve() throws IOException {
			dp[1][1] = arr[1][1];
			dp[2][1] = arr[2][1];
			
			for(int i=2; i<=N; i++) {
				dp[1][i] = Math.max(dp[2][i-1], dp[2][i-2]) + arr[1][i];
				dp[2][i] = Math.max(dp[1][i-1], dp[1][i-2]) + arr[2][i];
			}
			
			bw.write(String.valueOf(Math.max(dp[1][N], dp[2][N])) + "\n");
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				solve();
			}
			close();
		}
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

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
		private int[][] arr;
		private int[][] dp;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			arr = new int[N+1][4];
			dp = new int[1005][4];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				for(int j=1; j<=3; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		private int solution() {
			dp[1][1] = arr[1][1];
			dp[1][2] = arr[1][2];
			dp[1][3] = arr[1][3];
			
			for(int i=2; i<=N; i++) {
				dp[i][1] = Math.min(dp[i-1][2], dp[i-1][3]) + arr[i][1];
				dp[i][2] = Math.min(dp[i-1][1], dp[i-1][3]) + arr[i][2];
				dp[i][3] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[i][3]; 
			}
			
			return Math.min(Math.min(dp[N][1], dp[N][2]), dp[N][3]);
		}
		
		public void run() throws IOException {
			input();
			bw.write(String.valueOf(solution()));
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

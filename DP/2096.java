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
		private int[][] map;
		private int[][] dp;
		private int max = -1;
		private int min = 987654321;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N+1][4];
			dp = new int[N+1][4];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			init();
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				for(int j=1; j<=3; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		private void initDP() {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=3; j++) {
					dp[i][j] = 0;
				}
			}
		}
		
		private int getMax() {
			int max = -1;
			for(int i=1; i<=N; i++) {
				dp[i][1] = Math.max(dp[i-1][1], dp[i-1][2]) + map[i][1];
				dp[i][2] = Math.max(Math.max(dp[i-1][1], dp[i-1][2]), dp[i-1][3]) + map[i][2];
				dp[i][3] = Math.max(dp[i-1][2], dp[i-1][3]) + map[i][3];
				if(i==N) {
					max = Math.max(dp[i][1], Math.max(dp[i][2], dp[i][3])); 
				}
			}
			
			return max;
		}
		
		private int getMin() {
			int min = 987654321;
			for(int i=1; i<=N; i++) {
				dp[i][1] = Math.min(dp[i-1][1], dp[i-1][2]) + map[i][1];
				dp[i][2] = Math.min(Math.min(dp[i-1][1], dp[i-1][2]), dp[i-1][3]) + map[i][2];
				dp[i][3] = Math.min(dp[i-1][2], dp[i-1][3]) + map[i][3];
				if(i==N) {
					min = Math.min(dp[i][1], Math.min(dp[i][2], dp[i][3])); 
				}
			}
			
			return min;
		}
		
		private void solution() throws IOException {
			max = getMax();
			initDP();
			min = getMin();
			bw.write(String.valueOf(max) + " " + String.valueOf(min));
		}
		
		public void run() throws IOException {
			input();
			solution();
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

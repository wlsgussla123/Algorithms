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
		private int[][] map;
		private int[][] dp;
		private final int MAX = 987654321;
		private int ans = 0;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N+1][M+1];
			dp = new int[N+5][M+5];
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				char[] input = st.nextToken().toCharArray();
				for(int j=1; j<=M; j++) {
					map[i][j] = input[j-1] - '0';
					if(map[i][j] == 1) {
						dp[i][j] = 1;
					}
				}
			}
		}
		
		private void solve() throws IOException {
			int min = MAX;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(map[i][j] == 1) {
						min = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]);
						dp[i][j] = min + 1;
						ans = ans > dp[i][j] ? ans : dp[i][j];
					}
				}
			}
			
			bw.write(String.valueOf((int)Math.pow(ans, 2)));
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

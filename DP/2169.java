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
		private int N,M;
		private int[][] map = new int[1003][1003];
		private int[][] dp = new int[1003][1003];
		private int[][] temp = new int[2][1003]; // left and right
		private final int MIN = -1000000000;
		private final int[] dx = {0,0,1};
		private final int[] dy =  {1,-1,0};
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				for(int j=1; j<=M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		private int solution() {
			dp[1][1] = map[1][1];
			for(int i=2; i<=M; i++) {
				dp[1][i] += dp[1][i-1] + map[1][i]; 
			}
			
			for(int i=2; i<=N; i++) {
				temp[0][0] = dp[i-1][1];
				for(int j=1; j<=M; j++) {
					temp[0][j] = Math.max(temp[0][j-1], dp[i-1][j]) + map[i][j];
				}
				
				temp[1][M+1] = dp[i-1][M];
				for(int j=M; j>=1; j--) {
					temp[1][j] = Math.max(temp[1][j+1], dp[i-1][j]) + map[i][j];
				}
				
				for(int j=1; j<=M; j++) {
					dp[i][j] = Math.max(temp[0][j], temp[1][j]);
				}
			}
			
			return dp[N][M];
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

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
		private int[][] arr;
		private int[][] dp;
		private final int[] dx = {1,0,1};
		private final int[] dy = {0,1,1};
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			arr = new int[N+1][M+1];
			dp = new int[1005][1005];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				for(int j=1; j<=M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					dp[i][j] = -1;
				}
			}
		}
		
		private boolean checkArea(int x, int y) {
			return x>=1 && x<=N && y>=1 && y<=M;
		}
		
		private int solve(int x, int y) {
			if(!checkArea(x, y))
				return 0;
			
			if(x == N && y == M) {
				return arr[x][y];
			}
			
			if(dp[x][y] != -1) {
				return dp[x][y];
			}
			dp[x][y] = 0;
			
			for(int i=0; i<3; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				dp[x][y] = Math.max(dp[x][y], solve(nx, ny) + arr[x][y]);
			}
			
			return dp[x][y];
		}
		
		public void run() throws IOException {
			input();
			bw.write(String.valueOf(solve(1,1)));
			close();
		}
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

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
		private long[][] dp;
		private final int[] dx = {0,1};
		private final int[] dy = {1,0};
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N+1][N+1];
			dp = new long[N+1][N+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					dp[i][j] = -1;
				}
			}
		}

		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			init();
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				for(int j=1; j<=N; j++) {
					map[i][j
					       ] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		private boolean checkArea(int x, int y) {
			return x>=1 && x<=N && y>=1 && y<=N;
		}
		
		private long dfs(int x, int y) {
			if(x==N && y==N) return 1;
			if(dp[x][y] != -1) return dp[x][y];
			dp[x][y] = 0;
			
			for(int i=0; i<2; i++) {
				int nx = x + dx[i] * map[x][y];
				int ny = y + dy[i] * map[x][y];
				if(!checkArea(nx, ny)) continue;
				dp[x][y] += dfs(nx,ny);
			}
			
			return dp[x][y];
		}
		
		public void run() throws IOException {
			input();
			bw.write(String.valueOf(dfs(1,1)));
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

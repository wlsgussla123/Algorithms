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
		private int[][] map;
		private int[][] dp;
		private int N,M;
		private boolean[][] visited;
		private final int[] dx = {0,0,1,-1};
		private final int[] dy = {1,-1,0,0};
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N+1][M+1];
			dp = new int[N+1][M+1];
			visited = new boolean[N+1][M+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					dp[i][j] = -1;
				}
			}
		}

		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				for(int j=1; j<=M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		private boolean checkArea(int x, int y) {
			return x>=1 && x<=N && y>=1 && y<=M;
		}
		
		private int dfs(int x, int y) {
			if(x==N && y==M) {
				return 1;
			}
			if(dp[x][y] != -1) {
				return dp[x][y];
			}
			
			dp[x][y] = 0;
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(!checkArea(nx, ny) || visited[nx][ny]) continue;
				if(map[x][y] <= map[nx][ny]) continue;
				visited[nx][ny] = true;
				dp[x][y] += dfs(nx, ny);
				visited[nx][ny] = false;
			}
			return dp[x][y];
		}
		
		
		public void run() throws IOException {
			input();
			visited[1][1] = true;
			bw.write(String.valueOf(dfs(1,1)));
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

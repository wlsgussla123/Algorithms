package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}
	
	static class Position {
		int x;
		int y;
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Solution {
		private int N,M;
		private int[][] map;
		private int[][] dp;
		private final int[][] dirs = {{0,1}, {1,0}};
		private ArrayList<Position> pos = new ArrayList();
		private int ans = 0;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N][M];
			dp = new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					dp[i][j] = -1;
				}
			}
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						pos.add(new Position(i, j));
					}
				}
			}
		}
		
		private boolean checkArea(int x, int y) {
			return x>=0 && x<N && y>=0 && y<M;
		}
		
		private int dfs(int x, int y) {
			if(!checkArea(x, y)) return 0;
			if(dp[x][y] != -1) {
				return dp[x][y];
			}
			
			dp[x][y] = 0;
			int ret = 0;
			for(int i=0; i<2; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				
				ret = ret > dfs(nx, ny) ? ret : dfs(nx, ny);
			}
			dp[x][y] += ret;
			if(map[x][y] == 1) {
				dp[x][y]++;
			}
			
			return dp[x][y];
		}
		
		private void solution() throws IOException {
			int cnt = 0;
			for(Position node : pos) {
				cnt = dfs(node.x, node.y);
				ans = ans > cnt ? ans : cnt;
			}
			
			bw.write(String.valueOf(ans)+"\n");
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

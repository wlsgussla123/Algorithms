package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}

	static class Solution {
		private int N,M;
		private int[][] map;
		private boolean[][] visited;
		private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
		
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
			visited = new boolean[N+1][M+1];
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				char[] input = st.nextToken().toCharArray();
				for(int j=1; j<=M; j++) {
					map[i][j] = input[j-1] - '0';
				}
			}
		}
		
		private boolean checkArea(int x, int y) {
			return x>=1 && x<=N && y>=1 && y<=M;
		}
		
		private void solution() throws IOException {
			Queue<int[]> q = new LinkedList();
			int[] init = {1,1,1};
			visited[1][1] = true;
			q.add(init);

			while(!q.isEmpty()) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];
				int cnt = cur[2];
				
				if(x == N && y == M) {
					System.out.println(cnt);
					break;
				}
				
				for(int i=0; i<4; i++) {
					int nx = x + dirs[i][0];
					int ny = y + dirs[i][1];
					
					if(!checkArea(nx, ny) || visited[nx][ny] || map[nx][ny] == 0) continue;
					visited[nx][ny] = true;
					int[] next = {nx,ny,cnt+1};
					q.add(next);
				}
			}
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

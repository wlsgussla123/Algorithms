package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
		private boolean[][] visited;
		private final int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
		private int ans_time;
		private int ans_cnt;
//		private int cheese_cnt;
		private ArrayList<Position> pos = new ArrayList();
		private ArrayList<Position> remove_pos = new ArrayList();
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N][M];
			visited = new boolean[N][M];
			ans_time = 0;
			ans_cnt = 0;
//			cheese_cnt = 0;
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
//						cheese_cnt++;
						pos.add(new Position(i, j));
					}
				}
			}
		}
		
//		private boolean isOuter(int x, int y) {
//			for(int i=0; i<4; i++) {
//				int nx = x + dirs[i][0];
//				int ny = y + dirs[i][1];
//				
//				if(nx == 0 || nx == N-1 || ny == 0 || ny == M-1) {
//					return true;
//				}
//				if(visited[nx][ny] || map[nx][ny] != 0) continue;
//				
//				visited[nx][ny] = true;
//				if(isOuter(nx, ny)) {
//					return true;
//				}
//				visited[nx][ny] = false;
//			}
//			
//			return false;
//		}
		
//		private void findCheese() {
//			remove_pos = new ArrayList();
//			for(Position node : pos) {
//				int x = node.x;
//				int y = node.y;
//				visited = new boolean[N][M];
//				visited[x][y] = true;
//				
//				if(isOuter(x, y)) {
//					map[x][y] = 2;
//					remove_pos.add(node);
//				}
//			}
//			
//			for(int i=1; i<N-1; i++) {
//				for(int j=1; j<M-1; j++) {
//					if(map[i][j] != 1) continue;
//					visited = new boolean[N][M];
//					visited[i][j] = true;
//					if(isOuter(i, j)) {
//						map[i][j] = 2;
//					}
//				}
//			}
//		}
		
		private void findCheese() {
			Queue<Position> q = new LinkedList();
			for(Position node : pos) {
				q.add(node);
				visited = new boolean[N][M];
				visited[node.x][node.y] = true;
				
				while(!q.isEmpty()) {
					Position cur = q.poll();
					int x = cur.x;
					int y = cur.y;
					
					if(x == 0 || x == N-1 || y == 0 || y == M-1) {
						map[node.x][node.y] = 2;
						remove_pos.add(node);
						break;
					}
					
					for(int i=0; i<4; i++) {
						int nx = x + dirs[i][0];
						int ny = y + dirs[i][1];
						
						if(visited[nx][ny] || map[nx][ny] != 0) continue;
						visited[nx][ny] = true;
						q.add(new Position(nx, ny));
					}
				}
				q.clear();
			}
		}
		
		private void remove() {
			for(Position node : remove_pos) {
				int x = node.x;
				int y = node.y;
				map[x][y] = 0;
//				cheese_cnt--;
				pos.remove(node);
			}
			
//			for(int i=1; i<N-1; i++) {
//				for(int j=1; j<M-1; j++) {
//					if(map[i][j] == 2) {
//						map[i][j] = 0;
//						cheese_cnt--;
//					}
//				}
//			}
		}
		
		private void solution() throws IOException {
			ans_cnt = pos.size();
			while(pos.size() > 0) {
				findCheese();
				remove();
				ans_time++;
				ans_cnt = pos.size() == 0 ? ans_cnt : pos.size();
			}
			
			bw.write(String.valueOf(ans_time)+"\n");
			bw.write(String.valueOf(ans_cnt)+"\n");
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

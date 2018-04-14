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
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M;
		private int ans = 0;
		private int[][] map;
		private int[][] save_map;
		private boolean[][] w_visited;
		private boolean[][] v_visited;
		private final int[] dx = {0,0,1,-1};
		private final int[] dy = {1,-1,0,0};
		private final int BLANK=0, WALL=1, VIRUS=2;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N+1][M+1];
			save_map = new int[N+1][M+1];
			w_visited = new boolean[N+1][M+1];
			v_visited = new boolean[N+1][M+1];
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
		
		private void controlMap(int op) {
			// 맵 저장
			if(op == 0) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=M; j++) {
						save_map[i][j] = map[i][j];
					}
				}
			} else {
				// 맵 복원
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=M; j++) {
						map[i][j] = save_map[i][j];
					}
				}
			}
		}
		
		private boolean checkArea(int x, int y) {
			return x>=1 && x<=N && y>=1 && y<=M;
		}
		
		private int countArea() {
			int cnt = 0;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(map[i][j] == BLANK)
						cnt++;
				}
			}
			
			return cnt;
		}
		
		private void bfs() {
			v_visited = new boolean[N+1][M+1];
			Queue<int[]> q = new LinkedList();
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(map[i][j] == VIRUS) {
						int[] pos = {i,j};
						v_visited[i][j] = true;
						q.add(pos);
					}
				}
			}

			while(!q.isEmpty()) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];
				
				map[x][y] = VIRUS;
				
				for(int i=0; i<4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(!checkArea(nx, ny) || v_visited[nx][ny]) continue;
					if(map[nx][ny] == WALL || map[nx][ny] == VIRUS) continue;
					v_visited[nx][ny] = true;
					int[] next = {nx, ny};
					q.add(next);
				}
			}
			
			int cnt = countArea();
			ans = ans > cnt ? ans : cnt;
		}
		
		private void makeWall(int x, int y, int cnt) {
			if(cnt==3) {
				controlMap(0); // 저장
				bfs();
				controlMap(1);
				return;
			}
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(w_visited[i][j] || map[i][j] != BLANK) continue;
					w_visited[i][j] = true;
					map[i][j] = WALL;
					makeWall(i, j, cnt+1);
					map[i][j] = BLANK;
					w_visited[i][j] = false;
				}
			}
		}
		
		private void solve() {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(map[i][j] != BLANK) continue;
					w_visited[i][j] = true;
					map[i][j] = WALL;
					makeWall(i, j, 1);
					map[i][j] = BLANK;
					w_visited[i][j] = false;
				}
			}
		}
		
		public void run() throws IOException {
			input();
			solve();
			bw.write(String.valueOf(ans)+"\n");
			close();
		}
		
		private void print() {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

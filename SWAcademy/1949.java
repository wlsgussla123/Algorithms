package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Position {
	int x;
	int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,K;
		private int[][] map;
		private boolean[][] visited;
		private ArrayList<Position> list;
		private int ans;
		private final int[] dx = {0,0,1,-1};
		private final int[] dy = {1,-1,0,0};
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N][N];
			visited = new boolean[N][N];
			ans = 0;
			list = new ArrayList();
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			init();
			
			int max = 0;
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = max > map[i][j] ? max : map[i][j];
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(max == map[i][j]) {
						list.add(new Position(i, j));
					}
				}
			}
		}
		
		private boolean checkArea(int x, int y) {
			return x>=0 && x<N && y>=0 && y<N;
		}
		
		private void move(int x, int y, int k, int cnt, int[][] map) {
			ans = ans > cnt ? ans : cnt;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(!checkArea(nx, ny) || visited[nx][ny]) continue;
				if(map[nx][ny] >= map[x][y] && k==1) continue;
				
				if(map[nx][ny] < map[x][y]) {
					visited[nx][ny] = true;
					move(nx, ny, k, cnt+1, map);
					visited[nx][ny] = false;
				} else {
					for(int d=1; d<=K; d++) {
						if(map[nx][ny]-d >= map[x][y]) continue;
						map[nx][ny] = map[nx][ny]-d;
						visited[nx][ny] = true;
						move(nx, ny, 1, cnt+1, map);
						visited[nx][ny] = false;
						map[nx][ny] = map[nx][ny]+d;
					}
				}
			}
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				for(Position p : list) {
					visited[p.x][p.y] = true;
					move(p.x, p.y, 0, 1, map);
					visited[p.x][p.y] = false;
				}
				bw.write("#" + i + " " + String.valueOf(ans)+"\n");
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

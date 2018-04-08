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
		private int[][] map;
		private int ans = 0;
		private int sx,sy,sd;
		private final int[] dx = {-1,0,1,0};
		private final int[] dy = {0,1,0,-1};
		private final int BLANK = 0, WALL = 1, CLEAN = 2;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N][M];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			
			st = getStringTokenizer();
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			sd = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		private int rotateLeft(int d) {
			if(d==0) {
				return 3;
			} else if(d==1) {
				return 0;
			} else if(d==2) {
				return 1;
			} else {
				return 2;
			}
		}
		
		private int reverseDir(int d) {
			if(d==0) {
				return 2;
			} else if(d==1) {
				return 3;
			} else if(d==2) {
				return 0;
			} else {
				return 1;
			}
		}
		
		private void dfs(int x, int y, int dir) {
			if(map[x][y] == BLANK) {
				map[x][y] = CLEAN;
				ans++;
			}
			
			int d = dir;
			boolean isReverse = true;
			for(int i=0; i<4; i++) {
				d = rotateLeft(d);
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(map[nx][ny] != BLANK) continue;
				dfs(nx,ny,d);
				isReverse = false;
				break;
			} 
			
			// false이면 후진
			if(isReverse) {
				d = reverseDir(dir);
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(map[nx][ny] != WALL) {
					dfs(nx,ny,dir);
				}
			}
		}
		
		public void run() throws IOException {
			input();
			dfs(sx,sy,sd);
			bw.write(String.valueOf(ans));
			close();
		}
		
		private void print() {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
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

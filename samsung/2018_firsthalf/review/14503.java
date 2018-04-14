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
		private int[][] map;
		private int sx, sy, sd;
		private final int BLANK=0, WALL=1, CLEAN=2;
		private final int[] dx = {-1,0,1,0};
		private final int[] dy = {0,1,0,-1};
		
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
			map = new int[N][M];
			
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
		
		private int rotate(int dir) {
			if(dir == 0) return 3;
			else if(dir == 3) return 2;
			else if(dir == 2) return 1;
			else return 0;
		}
		
		private int reverseDir(int dir) {
			if(dir == 0) return 2;
			else if(dir == 1) return 3;
			else if(dir == 3) return 1;
			else return 0;
		}
		
		private void move(int x, int y, int dir) {
			map[x][y] = CLEAN;
			
			int d = dir;
			for(int i=0; i<4; i++) {
				d = rotate(d);
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(map[nx][ny] != BLANK) continue;
				move(nx, ny, d);
				return;
			}
			
			d = reverseDir(dir);
			int nx = x + dx[d];
			int ny = y + dy[d];
			if(map[nx][ny] != WALL) {
				move(nx,ny,dir);
			}
		}
		
		private int count() {
			int cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == CLEAN) cnt++;
				}
			}
			return cnt;
		}
		
		public void run() throws IOException {
			input();
			move(sx,sy,sd);
			bw.write(String.valueOf(count()));
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

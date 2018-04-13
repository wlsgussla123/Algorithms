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
		private int N,M;
		private int[][] map;
		private ArrayList<Position> q_list = new ArrayList();
		private ArrayList<Position> k_list = new ArrayList();
		private final int[] kx = {-2, -1, 1, 2, 2, 1, -1, -2};
		private final int[] ky = {1, 2, 2, 1, -1, -2 ,-2 ,-1};
		private final int[] qx = {0, 0, 1, -1, -1, 1, 1, -1};
		private final int[] qy = {1, -1, 0, 0,  1, 1, -1, -1};
		private final int BLANK = 0, WALL = 1, ATTACK = 2;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N+1][M+1];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			
			st = getStringTokenizer();
			int q = Integer.parseInt(st.nextToken());
			for(int i=0; i<q; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				q_list.add(new Position(x, y));
				map[x][y] = WALL;
			}
			
			st = getStringTokenizer();
			int k = Integer.parseInt(st.nextToken());
			for(int i=0; i<k; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				k_list.add(new Position(x, y));
				map[x][y] = WALL;
			}
			
			st = getStringTokenizer();
			int p = Integer.parseInt(st.nextToken());
			for(int i=0; i<p; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = WALL;
			}
		}
		
		private boolean checkArea(int x, int y) {
			return x>=1 && x<=N && y>=1 && y<=M;
		}
		
		private void attackByQueen(int x, int y) {
			for(int i=0; i<8; i++) {
				int nx = x + qx[i];
				int ny = y + qy[i];
				
				while(true) {
					if(!checkArea(nx, ny) || map[nx][ny] == WALL) break;
					map[nx][ny] = ATTACK;
					nx = nx + qx[i];
					ny = ny + qy[i];
				}
			}
		}
		
		private void attackByKnight(int x, int y) {
			for(int i=0; i<8; i++) {
				int nx = x + kx[i];
				int ny = y + ky[i];
				
				if(!checkArea(nx, ny) || map[nx][ny] == WALL) continue;
				map[nx][ny] = ATTACK;
			}
		}
		
		private int solution() {
			for(Position pos : q_list) {
				attackByQueen(pos.x, pos.y);
			}
			
			for(Position pos : k_list) {
				attackByKnight(pos.x, pos.y);
			}
			
			int cnt = 0;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(map[i][j] == BLANK) cnt++;
				}
			}
			
			return cnt;
		}
		
		public void run() throws IOException {
			input();
			bw.write(String.valueOf(solution()));
			solution();
			close();
		}
		
		private void print() {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

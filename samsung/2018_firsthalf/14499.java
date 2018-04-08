package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int[] dice = {0,0,0,0,0,0,0};
		private int[][] map;
		private int N,M,sx,sy,K;
		private final int[] dx = {0,0,0,-1,1};
		private final int[] dy = {0,1,-1,0,0};
		private ArrayList<Integer> list = new ArrayList();
		
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
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			init();
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			st = getStringTokenizer();
			for(int i=0; i<K; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		private boolean checkArea(int x, int y) {
			return x>=0 && x<N && y>=0 && y<M;
		}
		
		private void rollDice(int dir) {
			if(dir == 1) {
				int tmp = dice[1];
				dice[1] = dice[4];
				dice[4] = dice[6];
				dice[6] = dice[3];
				dice[3] = tmp;
			} else if(dir == 2) {
				int tmp = dice[1];
				dice[1] = dice[3];
				dice[3] = dice[6];
				dice[6] = dice[4];
				dice[4] = tmp;
			} else if(dir == 3) {
				int tmp = dice[1];
				dice[1] = dice[2];
				dice[2] = dice[6];
				dice[6] = dice[5];
				dice[5] = tmp;
			} else {
				int tmp = dice[1];
				dice[1] = dice[5];
				dice[5] = dice[6];
				dice[6] = dice[2];
				dice[2] = tmp;
			}
		}
		
		private void solution() throws IOException {
			int x = sx;
			int y = sy;
			for(Integer dir : list) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if(!checkArea(nx, ny)) continue;
				rollDice(dir);
				if(map[nx][ny] == 0) {
					map[nx][ny] = dice[6];
				} else {
					dice[6] = map[nx][ny];
					map[nx][ny] = 0;
				}
				bw.write(String.valueOf(dice[1])+"\n");
				x = nx;
				y = ny;
			}
			
		}
		
		public void run() throws IOException {
			input();
			solution();
			close();
		}
		
		private void print() {
			for(int i=1; i<=6; i++) {
				System.out.print(dice[i] + " ");
			}
			System.out.println();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

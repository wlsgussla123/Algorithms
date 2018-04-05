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
		private final int N=5,M=9;
		private char[][] map;
		private ArrayList<Position> list;
		private final char BLANK = '.', WALL = '#', PIN ='o';
		private final int MAX = 987654321;
		private final int[] dx = {0,0,1,-1};
		private final int[] dy = {1,-1,0,0};
		private int ans_pin = MAX, ans_move_cnt = MAX;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new char[N+1][M+1];
			list = new ArrayList();
			ans_pin = MAX;
			ans_move_cnt = MAX;
		}

		private void input() throws IOException {
			init();
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				char[] input = st.nextToken().toCharArray();
				for(int j=1; j<=M; j++) {
					map[i][j] = input[j-1];
					if(map[i][j] == PIN) {
						list.add(new Position(i, j));
					}
				}
			}
		}
		
		private boolean checkArea(int x, int y) {
			return x>=1 && x<=N && y>=1 && y<=M;
		}
		
		private void dfs(ArrayList<Position> list, int move_cnt) {
			int size = list.size();
			if(ans_pin > size) {
				ans_pin = size;
				ans_move_cnt = move_cnt;
			} else if(ans_pin == size) {
				ans_move_cnt = ans_move_cnt > move_cnt ? move_cnt : ans_move_cnt;
			}
			
			for(int i=0; i<size; i++) {
				Position p = list.get(i);
				int x = p.x;
				int y = p.y;
				for(int k=0; k<4; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];
					if(!checkArea(nx, ny) || map[nx][ny] != PIN) continue;
					int nnx = nx + dx[k];
					int nny = ny + dy[k];
					if(!checkArea(nnx, nny) || map[nnx][nny] != BLANK) continue;
					map[x][y] = BLANK;
					map[nx][ny] = BLANK;
					map[nnx][nny] = PIN;
					ArrayList<Position> tmp_list = new ArrayList();
					for(int a=1; a<=N; a++) {
						for(int b=1; b<=M; b++) {
							if(map[a][b] == PIN) {
								tmp_list.add(new Position(a, b));
							}
						}
					}
					dfs(tmp_list, move_cnt+1);
					map[x][y] = PIN;
					map[nx][ny] = PIN;
					map[nnx][nny] = BLANK;
				}
			}
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				dfs(list, 0);
				bw.write(String.valueOf(ans_pin) + " " + String.valueOf(ans_move_cnt)+"\n");
				if(i!=T) st = getStringTokenizer();
			}
			
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

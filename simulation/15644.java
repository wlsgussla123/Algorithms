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
		private char[][] map;
		private Position R, B;
		private final char WALL = '#', BLANK = '.', RED = 'R', BLUE = 'B', HOLE = 'O';
		private final int[] dx = {0,0,1,-1};
		private final int[] dy = {1,-1,0,0};
		private final int INF = 11;
		private int ans_cnt = INF;
		private ArrayList<Character> ans_list = new ArrayList();
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new char[N+1][M+1];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				char[] input = st.nextToken().toCharArray();
				for(int j=1; j<=M; j++) {
					map[i][j] = input[j-1];
					if(map[i][j] == 'R') {
						R = new Position(i, j);
					} else if(map[i][j] == 'B') {
						B  = new Position(i, j);
					}
				}
			}
		}
		
		
		// test 통과
		private boolean isRedFirst(Position R, Position B, int dir) {
			if(dir == 0) {
				if(R.x != B.x)
					return false;
				else {
					if(R.y > B.y) 
						return true;
					else 
						return false;
				}
			} else if(dir == 1) {
				if(R.x != B.x)
					return false;
				else {
					if(R.y < B.y) 
						return true;
					else
						return false;
				}
			} else if(dir == 2) {
				if(R.y != B.y)
					return false;
				else {
					if(R.x > B.x)
						return true;
					else 
						return false;
				}
			} else {
				if(R.y != B.y)
					return false;
				else {
					if(R.x < B.x)
						return true;
					else
						return false;
				}
			}
		}
		
		private boolean isReverse(int dir, int next) {
			if(dir == 0 && next == 1) return true;
			else if(dir == 1 && next == 0) return true;
			else if(dir == 2 && next == 3) return true;
			else if(dir == 3 && next == 2) return true;
			return false;
		}
		
		private void dfs(int dir, Position R, Position B, int cnt, ArrayList<Character> list, char[][] temp_map) throws IOException {
			if(cnt > 10 || cnt > ans_cnt) return;
			
			boolean red = isRedFirst(R, B, dir);
			boolean r = false;
			boolean b = false;
			
			char[][] map = new char[N+1][M+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					map[i][j] = temp_map[i][j];
				}
			}
			
			// 빨강 먼저 굴리자
			if(red) {
				int x = R.x;
				int y = R.y;
				while(true) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					
					if(map[nx][ny] == HOLE) {
						map[R.x][R.y] = BLANK;
						r = true;
						break;
					}
					if(map[nx][ny] == WALL) {
						map[R.x][R.y] = BLANK;
						map[x][y] = RED;
						R.x = x;
						R.y = y;
						break;
					}
					x = nx;
					y = ny;
				}
				
				x = B.x;
				y = B.y;
				while(true) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					
					if(map[nx][ny] == HOLE) {
						map[B.x][B.y] = BLANK;
						b = true;
						break;
					}
					if(map[nx][ny] == WALL || map[nx][ny] == RED) {
						map[B.x][B.y] = BLANK;
						map[x][y] = BLUE;
						B.x = x;
						B.y = y;
						break;
					}
					x = nx;
					y = ny;
				}
			} else {
				int x = B.x;
				int y = B.y;
				while(true) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					
					if(map[nx][ny] == HOLE) {
						map[B.x][B.y] = BLANK;
						b = true;
						break;
					}
					if(map[nx][ny] == WALL) {
						map[B.x][B.y] = BLANK;
						map[x][y] = BLUE;
						B.x = x;
						B.y = y;
						break;
					}
					x = nx;
					y = ny;
				}
				
				x = R.x;
				y = R.y;
				while(true) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					
					if(map[nx][ny] == HOLE) {
						map[R.x][R.y] = BLANK;
						r = true;
						break;
					}
					if(map[nx][ny] == WALL || map[nx][ny] == BLUE) {
						map[R.x][R.y] = BLANK;
						map[x][y] = RED;
						R.x = x;
						R.y = y;
						break;
					}
					x = nx;
					y = ny;
				}
			}
			
			if(r && !b) {
				if(ans_cnt > cnt) {
					ans_cnt = cnt;
					ans_list.clear();
					ans_list.addAll(list);
				}
				return;
			}
			if(b) {
				return;
			}

			for(int i=0; i<4; i++) {
				if(dir == i || isReverse(dir, i)) continue;
				ArrayList<Character> temp = new ArrayList();
				int size = list.size();
				for(int j=0; j<size; j++) {
					temp.add(list.get(j));
				}

				if(i==0) {
					temp.add('R');
				} else if(i==1) {
					temp.add('L');
				} else if(i==2) {
					temp.add('D');
				} else {
					temp.add('U');
				}
				dfs(i, new Position(R.x, R.y), new Position(B.x, B.y), cnt+1, temp, map);
			}
		}
		
		private void solution() throws IOException {
			for(int i=0; i<4; i++) {
				ArrayList<Character> temp = new ArrayList();
				if(i==0) {
					temp.add('R');
				} else if(i==1) {
					temp.add('L');
				} else if(i==2) {
					temp.add('D');
				} else {
					temp.add('U');
				}
				dfs(i, new Position(R.x, R.y), new Position(B.x, B.y), 1, temp, map);
			}
		}
		
		public void run() throws IOException {
			input();
			solution();
			if(ans_cnt == INF) bw.write("-1\n");
			else bw.write(String.valueOf(ans_cnt)+"\n");
			for(Character c : ans_list) {
				bw.write(c);
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

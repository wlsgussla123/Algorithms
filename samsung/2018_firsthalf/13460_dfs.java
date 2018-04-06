package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
		private final char WALL='#', BLANK='.', HOLE='O', RED='R', BLUE='B';
		private final int[] dx = {0,0,1,-1};
		private final int[] dy = {1,-1,0,0};
		private final int MAX = 987654321;
		private int ans = MAX;
		
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
						B = new Position(i, j);
					}
				}
			}
		}
		
		private boolean isReverse(int dir, int idx) {
			if(dir == 0 && idx == 1) return true;
			else if(dir == 1 && idx == 0) return true;
			else if(dir == 2 && idx == 3) return true;
			else if(dir == 3 && idx == 2) return true;
			return false;
		}
		
		private boolean redFirst(int dir, Position R, Position B) {
			if(dir==0) {
				if(R.x == B.x) {
					return R.y > B.y ? true : false;
				}
			} else if(dir==1) {
				if(R.x == B.x) {
					return B.y > R.y ? true : false;
				}
			} else if(dir==2) {
				if(R.y == B.y) {
					return R.x > B.x ? true : false;
				}
			} else if(dir==3) {
				if(R.y == B.y) {
					return B.x > R.x ? true : false;
				}
			}
			
			return false;
		}
		
		private void dfs(Position R, Position B, int cnt, int dir, char[][] map) {
			if(cnt>10 || cnt >= ans) return;
//			System.out.println(dir + " " + R.x + " " + R.y + " " + B.x + " " + B.y + " " + cnt);
			
			char[][] tmp = new char[N+1][M+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					tmp[i][j] = map[i][j];
				}
			}
			
//			for(int i=1; i<=N; i++) {
//				for(int j=1; j<=M; j++) {
//					System.out.print(tmp[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			boolean redFirst = false;
			if(redFirst(dir,R,B)) {
				redFirst = true;
			}
			
			int rx = R.x;
			int ry = R.y;
			int bx = B.x;
			int by = B.y;
			boolean r = false;
			boolean b = false;
			
			if(redFirst) {
				int nrx = rx + dx[dir];
				int nry = ry + dy[dir];
				while(true) {
					if(tmp[nrx][nry] == WALL) break;
					if(tmp[nrx][nry] == HOLE) {
						tmp[rx][ry] = BLANK;
						r = true;
						break;
					}
					tmp[rx][ry] = BLANK;
					tmp[nrx][nry] = RED;
					rx = nrx;
					ry = nry;
					nrx = nrx + dx[dir];
					nry = nry + dy[dir];
				}

				int nbx = bx + dx[dir];
				int nby = by + dy[dir];
				while(true) {
					if(tmp[nbx][nby] == WALL || tmp[nbx][nby] == RED) break;
					if(tmp[nbx][nby] == HOLE) {
						tmp[bx][by] = BLANK;
						b = true;
						break;
					}
					tmp[bx][by] = BLANK;
					tmp[nbx][nby] = BLUE;
					bx = nbx;
					by = nby;
					nbx = nbx + dx[dir];
					nby = nby + dy[dir];
				}
			} else {
				int nbx = bx + dx[dir];
				int nby = by + dy[dir];
				while(true) {
					if(tmp[nbx][nby] == WALL) break;
					if(tmp[nbx][nby] == HOLE) {
						tmp[bx][by] = BLANK;
						b = true;
						break;
					}
					tmp[bx][by] = BLANK;
					tmp[nbx][nby] = BLUE;
					bx = nbx;
					by = nby;
					nbx = nbx + dx[dir];
					nby = nby + dy[dir];
				}
				
				int nrx = rx + dx[dir];
				int nry = ry + dy[dir];
				while(true) {
					if(tmp[nrx][nry] == WALL || tmp[nrx][nry] == BLUE) break;
					if(tmp[nrx][nry] == HOLE) {
						r = true;
						tmp[rx][ry] = BLANK;
						break;
					}
					tmp[rx][ry] = BLANK;
					tmp[nrx][nry] = RED;
					rx = nrx;
					ry = nry;
					nrx = nrx + dx[dir];
					nry = nry + dy[dir];
				}
			}

			if(r && !b) {
				ans = ans > cnt ? cnt : ans;
				return;
			} else if(r || b) {
				return;
			}
			
			Position nr = new Position(rx, ry);
			Position nb = new Position(bx, by);
			for(int i=0; i<4; i++) {
				if(dir==i || isReverse(dir, i)) continue;
				dfs(nr, nb, cnt+1, i, tmp);
			}
		}
		
		public void run() throws IOException {
			input();
			for(int i=0; i<4; i++) {
				dfs(R,B,1,i,map);
			}
			if(ans==MAX) bw.write(String.valueOf(-1));
			else bw.write(String.valueOf(ans));
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

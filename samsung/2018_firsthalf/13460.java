package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Info {
	int rx;
	int ry;
	int bx;
	int by;
	int cnt;
	int dir;
	char[][] map;
	public Info(int rx, int ry, int bx, int by, int cnt, int dir, char[][] map, int N, int M) {
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
		this.cnt = cnt;
		this.dir = dir;
		this.map = new char[N+1][M+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				this.map[i][j] = map[i][j];
			}
		}
	}
}

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M;
		private char[][] map;
		private int rx,ry,bx,by;
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
						rx = i;
						ry = j;
					} else if(map[i][j] == 'B') {
						bx = i;
						by = j;
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
		
		private void bfs() {
			Queue<Info> q = new LinkedList();
			q.add(new Info(rx, ry, bx, by, 0, -1, map, N, M));
			while(!q.isEmpty()) {
				Info cur = q.poll();
				int orx = cur.rx;
				int ory = cur.ry;
				int obx = cur.bx;
				int oby = cur.by;
				int cnt = cur.cnt;
				int dir = cur.dir;
//				System.out.println(orx + " " + ory + " " + obx + " " + oby + " " + cnt);
//				for(int i=1; i<=N; i++) {
//					for(int j=1; j<=M; j++) {
//						System.out.print(cur.map[i][j]);
//					}
//					System.out.println();
//				}
//				System.out.println();

				// 종료 체크
				if(cnt>10) return;
				if(cur.map[obx][oby] == HOLE) continue;
				if(cur.map[orx][ory] == HOLE) {
					ans = ans > cnt ? cnt : ans;
					return;
				}
				
				for(int i=0; i<4; i++) {
					if(isReverse(dir, i)) {
						continue;
					}
					
					char[][] tmp_map = new char[N+1][M+1];
					for(int a=1; a<=N; a++) {
						for(int b=1; b<=M; b++) {
							tmp_map[a][b] = cur.map[a][b];
						}
					}

					int rx = orx;
					int ry = ory;
					int bx = obx;
					int by = oby;
					int nrx = rx + dx[i];
					int nry = ry + dy[i];
					int nbx = bx + dx[i];
					int nby = by + dy[i];
					
					// 빨간 공보다 파란 공이 앞서있을 경우 : 파란공부터 굴려라.
					if((i==0 && (rx==bx&&by>ry)) || (i==1 && (rx==bx&&by<ry)) || (i==2 && (ry==by&&bx>rx)) || (i==3 && (ry==by&&rx>bx))) {
						tmp_map[bx][by] = BLANK;
						while(true) {
							// 범위를 벗어나거나, 벽인 경우 종료.
							if(tmp_map[nbx][nby] == WALL) {
								tmp_map[bx][by] = BLUE;
								break;
							} else {
								// 만약 파란 공이 구멍에 넣고 종료.
								if(tmp_map[nbx][nby] == HOLE) {
									bx = nbx;
									by = nby;
									break;
								}
								// 전진한 정보를 bx, by에 넣어줌.
								bx = nbx;
								by = nby;
								// 한 칸 더 전진.
								nbx = nbx + dx[i];
								nby = nby + dy[i];
							}
						}
						
						tmp_map[rx][ry] = BLANK;
						while(true) {
							if(tmp_map[nrx][nry] == WALL || tmp_map[nrx][nry] == BLUE) {
								tmp_map[rx][ry] = RED;
								break;
							} else {
								if(tmp_map[nrx][nry] == HOLE) {
									rx = nrx;
									ry = nry;
									break;
								}
								rx = nrx;
								ry = nry;
								nrx = nrx + dx[i];
								nry = nry + dy[i];
							}
						}
						q.add(new Info(rx, ry, bx, by, cnt+1, i, tmp_map, N, M));
					} 
					// 파란 공보다 빨간 공이 앞서있을 경우나 둘이 안 겹칠 경우 빨간 공부터 굴리자.
					else {
						tmp_map[rx][ry] = BLANK;
						while(true) {
							if(tmp_map[nrx][nry] == WALL) {
								tmp_map[rx][ry] = RED;
								break;
							} else {
								if(tmp_map[nrx][nry] == HOLE) {
									rx = nrx;
									ry = nry;
									break;
								}
								
								rx = nrx;
								ry = nry;
								nrx = nrx + dx[i];
								nry = nry + dy[i];
							}
						}
						
						tmp_map[bx][by] = BLANK;
						while(true) {
							if(tmp_map[nbx][nby] == WALL || tmp_map[nbx][nby] == RED) {
								tmp_map[bx][by] = BLUE;
								break;
							} else {
								if(tmp_map[nbx][nby] == HOLE) {
									bx = nbx;
									by = nby;
									break;
								}
								
								bx = nbx;
								by = nby;
								nbx = nbx + dx[i];
								nby = nby + dy[i];
							}
						}
						q.add(new Info(rx, ry, bx, by, cnt+1, i, tmp_map, N, M));
					} 
				}
			}
		}
		
		public void run() throws IOException {
			input();
			bfs();
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

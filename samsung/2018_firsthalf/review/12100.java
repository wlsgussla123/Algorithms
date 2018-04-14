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
		private int N;
		private int[][] map;
		private final int BLANK=0;
		private int ans = 0;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N+1][N+1];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				for(int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		private void changeMap(int[][] temp_map, int dir) {
			if(dir == 0) {
				for(int i=1; i<=N; i++) {
					for(int j=N-1; j>=1; j--) {
						for(int k=j+1; k<=N; k++) {
							if(temp_map[i][k] == BLANK) {
								temp_map[i][k] = temp_map[i][k-1];
								temp_map[i][k-1] = BLANK;
							} else {
								break;
							}
						}
					}
				}
			} else if(dir == 1) {
				for(int i=1; i<=N; i++) {
					for(int j=2; j<=N; j++) {
						for(int k=j-1; k>=1; k--) {
							if(temp_map[i][k] == BLANK) {
								temp_map[i][k] = temp_map[i][k+1];
								temp_map[i][k+1] = BLANK;
							} else {
								break;
							}
						}
					}
				}
			} else if(dir == 2) {
				for(int j=1; j<=N; j++) {
					for(int i=N-1; i>=1; i--) {
						for(int k=i+1; k<=N; k++) {
							if(temp_map[k][j] == BLANK) {
								temp_map[k][j] = temp_map[k-1][j];
								temp_map[k-1][j] = BLANK;
							} else {
								break;
							}
						}
					}
				}
			} else {
				for(int j=1; j<=N; j++) {
					for(int i=2; i<=N; i++) {
						for(int k=i-1; k>=1; k--) {
							if(temp_map[k][j] == BLANK) {
								temp_map[k][j] = temp_map[k+1][j];
								temp_map[k+1][j] = BLANK;
							} else {
								break;
							}
						}
					}
				}
			}			
		}
		
		private void addBlock(int[][] temp_map, int dir) {
			if(dir == 0) {
				for(int i=1; i<=N; i++) {
					for(int j=N; j>1; j--) {
						if(temp_map[i][j] == BLANK) continue;
						if(temp_map[i][j] == temp_map[i][j-1]) {
							temp_map[i][j] *= 2;
							temp_map[i][j-1] = BLANK;
						}
					}
				}
			} else if(dir == 1) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<N; j++) {
						if(temp_map[i][j] == BLANK) continue;
						if(temp_map[i][j] == temp_map[i][j+1]) {
							temp_map[i][j] *= 2;
							temp_map[i][j+1] = BLANK;
						}
					}
				}
			} else if(dir == 2) {
				for(int j=1; j<=N; j++) {
					for(int i=N; i>1; i--) {
						if(temp_map[i][j] == BLANK) continue;
						if(temp_map[i][j] == temp_map[i-1][j]) {
							temp_map[i][j] *= 2;
							temp_map[i-1][j] = BLANK;
						}
					}
				}
			} else {
				for(int j=1; j<=N; j++) {
					for(int i=1; i<N; i++) {
						if(temp_map[i][j] == BLANK) continue;
						if(temp_map[i][j] == temp_map[i+1][j]) {
							temp_map[i][j] *= 2;
							temp_map[i+1][j] = BLANK;
						}
					}
				}
			}
		}
		
		private void move(int dir, int[][] map, int cnt) {
			if(cnt > 5) {
				int max = 0;
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						if(map[i][j] > max) {
							max = map[i][j];
						}
					}
				}
				
				ans = ans > max ? ans : max;
				return;
			}
			
			int[][] temp_map = new int[N+1][N+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					temp_map[i][j] = map[i][j];
				}
			}
			
			changeMap(temp_map, dir);
			addBlock(temp_map, dir);
			changeMap(temp_map, dir);
			
			for(int i=0; i<4; i++) {
				move(i, temp_map, cnt+1);
			}
		}
		
		private void solution() {
			for(int i=0; i<4; i++) {
				move(i, map, 1);
			}			
		}
		
		public void run() throws IOException {
			input();
			solution();
			bw.write(String.valueOf(ans)+"\n");
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

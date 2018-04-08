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
		private int ans = -1;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}

		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			map = new int[N+1][N+1];
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				for(int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		private int findMax(int[][] map) {
			int max = 0;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					max = max > map[i][j] ? max : map[i][j];
				}
			}
			return max;
		}
		
		private void move(int dir, int[][] map) {
			if(dir == 0) {
				for(int i=1; i<=N; i++) {
					for(int j=N-1; j>=1; j--) {
						for(int k=j+1; k<=N; k++) {
							if(map[i][k] == 0) {
								map[i][k] = map[i][k-1];
								map[i][k-1] = 0;
							}
						}
					}
				}
			} else if(dir == 1) {
				for(int i=1; i<=N; i++) {
					for(int j=2; j<=N; j++) {
						for(int k=j-1; k>=1; k--) {
							if(map[i][k] == 0) {
								map[i][k] = map[i][k+1];
								map[i][k+1] = 0;
							}
						}
					}
				}
			} else if(dir == 2) {
				for(int j=1; j<=N; j++) {
					for(int i=N-1; i>=1; i--) {
						for(int k=i+1; k<=N; k++) {
							if(map[k][j] == 0) {
								map[k][j] = map[k-1][j];
								map[k-1][j] = 0;
							}
						}
					}
				}
			} else {
				for(int j=1; j<=N; j++) {
					for(int i=2; i<=N; i++) {
						for(int k=i-1; k>=1; k--) {
							if(map[k][j] == 0) {
								map[k][j] = map[k+1][j];
								map[k+1][j] = 0;
							}
						}
					}
				}
			}
		}
		
		private void addBlock(int dir, int[][] map) {
			if(dir == 0) {
				for(int i=1; i<=N; i++) {
					for(int j=N; j>=2; j--) {
						if(map[i][j] == map[i][j-1]) {
							map[i][j] *= 2;
							map[i][j-1] = 0;
						}
					}
				}
			} else if(dir == 1) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N-1; j++) {
						if(map[i][j] == map[i][j+1]) {
							map[i][j] *= 2;
							map[i][j+1] = 0;
						}
					}
				}
			} else if(dir == 2) {
				for(int j=1; j<=N; j++) {
					for(int i=N; i>=2; i--) {
						if(map[i][j] == map[i-1][j]) {
							map[i][j] *= 2;
							map[i-1][j] = 0;
						}
					}
				}
			} else {
				for(int j=1; j<=N; j++) {
					for(int i=1; i<=N-1; i++) {
						if(map[i][j] == map[i+1][j]) {
							map[i][j] *= 2;
							map[i+1][j] = 0;
						}
					}
				}
			}
		}
		
		private void dfs(int dir, int cnt, int[][] map) throws IOException {
			if(cnt == 5) {
				int max = findMax(map);
				ans = ans > max ? ans : max;
				return;
			}
			
			int[][] tmp = new int[N+1][N+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					tmp[i][j] = map[i][j];
				}
			}
			
			move(dir, tmp);
			addBlock(dir, tmp);
			move(dir, tmp);
			for(int i=0; i<4; i++) {
				dfs(i, cnt+1, tmp);
			}
		}
		
		public void run() throws IOException {
			input();
			for(int i=0; i<4; i++) {
				dfs(i,0,map);
			}
			bw.write(String.valueOf(ans));
			close();
		}
		
		private void print(int[][] map) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					System.out.print(map[i][j]);
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

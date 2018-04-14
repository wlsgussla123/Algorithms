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
		private final int MAXN = 11;
		private final int MAXT = 11 * 2 * 11 * 11;
		private int N;
		private int[][] map;
		private int[] match;
		
		private int ans;
		private Position[] man, stair;
		private int M,S;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			map = new int[N+1][N+1]; // 주의
			man = new Position[N*N];
			stair = new Position[2];
			M = S = 0;
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				for(int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						man[M++] = new Position(i, j);
					}
					if(map[i][j] >= 2) {
						stair[S++] = new Position(i, j);
					}
				}
			}
			ans = 987654321;
			match = new int[M];
		}
		
		private int dist(int man_idx, int stair_idx) {
			int dx = Math.abs(man[man_idx].x - stair[stair_idx].x);
			int dy = Math.abs(man[man_idx].y - stair[stair_idx].y);
			return dx + dy;
		}
		
		private void dfs(int man_idx) {
			if(man_idx == M) {
				update();
				return;
			}
			
			for(int stair_idx = 0; stair_idx < 2; stair_idx++) {
				match[man_idx] = stair_idx;
				dfs(man_idx + 1);
			}
		}
		
		private void update() {
			int total_min_time = 0;
			
			for(int stair_idx = 0; stair_idx < 2; stair_idx++) {
				Position now_stair = stair[stair_idx];
				int[] arrival_time = new int[MAXN * 2];
				int[] current_stair = new int[MAXT];
				
				for(int i=0; i < MAXT; i++) current_stair[i] = 0;
				for(int i=0; i < 2 * MAXN; i++) arrival_time[i] = 0;
				
				for(int man_idx = 0; man_idx < M; man_idx++) {
					if(match[man_idx] == stair_idx) {
						arrival_time[dist(man_idx, stair_idx) + 1]++;
					}
				}
				
				int now_min_time = 0;
				for(int time = 1; time <= 20; time++) {
					while(arrival_time[time] > 0) {
						arrival_time[time]--;
						int remain_time = map[now_stair.x][now_stair.y];
						
						for(int walk_time = time; walk_time < MAXT; walk_time++) {
							if(current_stair[walk_time] < 3) {
								current_stair[walk_time]++;
								remain_time--;
								
								if(remain_time==0) {
									now_min_time = Math.max(now_min_time, walk_time + 1);
									break;
								}
							}
						}
					}
				}
				total_min_time = Math.max(total_min_time, now_min_time);
			}
			ans = Math.min(ans, total_min_time);
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				dfs(0);
				bw.write("#" + i + " " + String.valueOf(ans)+"\n");
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

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
		private int N,M,R,C,L;
		private int[][] map;
		private boolean[][] visited;
		private int ans;
		private final int[] dx = {0,0,0,1,-1};
		private final int[] dy = {0,1,-1,0,0};
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N][M];
			visited = new boolean[N][M];
			ans = 0;
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		private boolean checkArea(int x, int y) {
			return x>=0 && x<N && y>=0 && y<M;
		}
		
		private boolean isConnected(int cur, int prev) {
			if(prev == 1) {
				if(cur == 1 || cur == 3 || cur == 6 || cur == 7) return true;
			} else if(prev == 2) {
				if(cur == 1 || cur == 3 || cur == 4 || cur == 5) return true;
			} else if(prev == 3) {
				if(cur == 1 || cur == 2 || cur == 4 || cur == 7) return true;
			} else {
				if(cur == 1 || cur == 2 || cur == 5 || cur == 6) return true;
			}
			
			return false;
		}
		
		private void bfs() {
			Queue<int[]> q = new LinkedList();
			int[] a = {R,C,0,0};
			q.add(a);
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];
				int time = cur[2];
				int prev_dir = cur[3];
				if(time == L) {
					continue;
				}
				if(!checkArea(x, y) || map[x][y] == 0 || visited[x][y]) {
					continue;
				}
				if(time != 0 && !isConnected(map[x][y], prev_dir)) {
					continue;
				}
				
				ans++;
				visited[x][y] = true;
				int dir = map[x][y];
				// 1 : 동, 2 : 서, 3: 남, 4: 북
				for(int i=1; i<=4; i++) {
					if(dir == 2 && (i==1 || i==2)) continue;
					if(dir == 3 && (i==3 || i==4)) continue;
					if(dir == 4 && (i==2 || i==3)) continue;
					if(dir == 5 && (i==2 || i==4)) continue;
					if(dir == 6 && (i==1 || i==4)) continue;
					if(dir == 7 && (i==1 || i==3)) continue;
					
					int nx = x + dx[i];
					int ny = y + dy[i];
					int[] next = {nx, ny, time+1, i};
					q.add(next);
				}
			}
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				bfs();
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

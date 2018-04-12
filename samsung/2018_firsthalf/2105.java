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
		private boolean[] number;
		private final int[] dx = {1,1,-1,-1};
		private final int[] dy = {1,-1,-1,1};
		private int ans;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N+1][N+1];
			number = new boolean[101];
			ans = -1;
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
		
		private boolean checkArea(int x, int y) {
			return x>=1 && x<=N && y>=1 && y<=N;
		}
		
		private void dfs(int x, int y, int sx, int sy, int dir, int cnt) {
			if(dir == 3 && x == sx && y == sy) {
				ans = ans > cnt ? ans : cnt;
				return;
			}
			
			if(number[map[x][y]]) return;
			number[map[x][y]] = true;
			
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if(checkArea(nx, ny)) {
				dfs(nx, ny, sx, sy, dir, cnt+1);
			}
			
			if(dir<3) {
				nx = x + dx[dir+1];
				ny = y + dy[dir+1];
				if(checkArea(nx, ny)) {
					dfs(nx, ny, sx, sy, dir+1, cnt+1);
				} else {
					number[map[x][y]] = false;
					return;
				}
			}
			
			number[map[x][y]] = false;
		}
		
		private void solution() throws IOException {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					dfs(i,j,i,j,0,0);
				}
			}
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				solution();
				bw.write("#"+ i + " " + String.valueOf(ans)+"\n");
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

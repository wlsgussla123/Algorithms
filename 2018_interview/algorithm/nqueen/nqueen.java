package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N;
		private int cnt = 0;
		private int[] col;
		private boolean[][] visited;
		private final int[] dx = {-1, 1, 1, -1};
		private final int[] dy = {1, 1, -1, -1};
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			col = new int[N+1];
			visited = new boolean[N+1][N+1];
		}
		
		private boolean checkArea(int x, int y) {
			return x>=1 && x<=N && y>=1 && y<=N;
		}
		
		private boolean checkDiagnal(int x, int y) {
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				while(true) {
					if(!checkArea(nx, ny)) break;
					if(visited[nx][ny]) {
						return false;
					}
					
					nx = nx + dx[i];
					ny = ny + dy[i];
				}
			}
			
			return true;
		}
		
		private boolean isPromising(int row, int idx) {
			if(col[idx] != 0 || !checkDiagnal(row, idx))
				return false;
			
			return true;
		}
		
		private void nqueen(int row) {
			if(row > N) {
				cnt++;
				return;
			}
			
			for(int i=1; i<=N; i++) {
				if(isPromising(row, i)) {
					col[i] = row;
					visited[row][i] = true;
					nqueen(row+1);
					col[i] = 0;
					visited[row][i] = false;
				}
			}
		}
		
		private void solve() {
			nqueen(1);
		}
		
		public void run() throws IOException {
			input();
			solve();
			System.out.println(cnt);
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

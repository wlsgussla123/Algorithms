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
		private int N,M;
		private int[][] map;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N+1][N+1];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			st = getStringTokenizer();
			M = Integer.parseInt(st.nextToken());
			init();
		
			int from, to;
			for(int i=1; i<=M; i++) {
				st = getStringTokenizer();
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				map[from][to] = 1;
				map[to][from] = 1;
			}
		}
		
		private void solve() throws IOException {
			for(int k=1; k<=N; k++) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						if(map[i][j] == 1) continue;
						if(map[i][k] == 1 && map[k][j] == 1) {
							map[i][j] = 1;
						}
					}
				}
			}
			
			int cnt = 0;
			for(int i=2; i<=N; i++) {
				if(map[1][i] == 1) cnt++;
			}
	
			bw.write(String.valueOf(cnt));
		}
		
		public void run() throws IOException {
			input();
			solve();
			close();
		}
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

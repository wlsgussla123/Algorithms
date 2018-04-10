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
		private int N,M,C;
		private int[][] map;
		private int[][] memo;
		private int ans;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N][N];
			memo = new int[N][N];
			ans = 0;
		}
		
		private int collect(int x, int y, int sum, int psum, int m) {
			if(m>=M) 
				return psum;
			
			int max = 0;
			int next = 0;
			for(int i=m; i<M; i++) {
				if(sum + map[x][y+i] <= C) {
					next = collect(x, y, sum + map[x][y+i], psum + map[x][y+i] * map[x][y+i], i+1);
					if(max < next) max = next;
				} else {
					next = collect(x, y, sum, psum, i+1);
					if(max < next) max = next;
				}
			}
			
			return max;
		}
		
		private int getMax(int x, int y) {
			int max = 0;
			for(int i=0; i<N; i++) {
				if(i==x) {
					for(int j=y+M; j<=N-M; j++) {
						max = max > memo[i][j] ? max : memo[i][j];
					}
				} else {
					for(int j=0; j<=N-M; j++) {
						max = max > memo[i][j] ? max : memo[i][j];
					}
				}
			}
			
			return max;
		}
		
		private void solution() {
			for(int i=0; i<N; i++) {
				for(int j=0; j<=N-M; j++) {
					memo[i][j] = collect(i, j, 0, 0, 0); 
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<=N-M; j++) {
					int n = getMax(i, j) + memo[i][j];
					ans = ans > n ? ans : n;
				}
			}
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
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

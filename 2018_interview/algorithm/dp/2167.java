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
		private int N,M,K;
		private int[][] arr;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			arr = new int[N+1][M+1];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				for(int j=1; j<=M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			st = getStringTokenizer();
			K = Integer.parseInt(st.nextToken());
			for(int i=1; i<=K; i++) {
				st = getStringTokenizer();
				int sx = Integer.parseInt(st.nextToken());
				int sy = Integer.parseInt(st.nextToken());
				int ex = Integer.parseInt(st.nextToken());
				int ey = Integer.parseInt(st.nextToken());
				
				int sum = 0;
				for(int x=sx; x<=ex; x++) {
					for(int y=sy; y<=ey; y++) {
						sum += arr[x][y];
					}
				}
				bw.write(String.valueOf(sum)+"\n");
			}
		}
		
		private void solve() throws IOException {
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

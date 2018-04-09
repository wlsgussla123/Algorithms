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
		private boolean[] visited;
		private int ans;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N][N];
			visited = new boolean[N];
			ans = 987654321;
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		private void dfs(int idx, int cnt) {
			if(cnt == N/2) {
				int A = 0;
				int B = 0;
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(visited[i] && visited[j]) {
							A += map[i][j];
						} else if(!visited[i] && !visited[j]) {
							B += map[i][j];
						}
					}
				}
				int sum = Math.abs(A-B);
				ans = ans > sum ? sum : ans;
				
				return;
			}
			
			for(int i=idx+1; i<N; i++) {
				visited[i] = true;
				dfs(i,cnt+1);
				visited[i] = false;
			}
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int idx=1; idx<=T; idx++) {
				input();
				for(int i=0; i<N; i++) {
					visited[i] = true;
					dfs(i,1);
					visited[i] = false;
				}
				bw.write("#"+ idx + " " + String.valueOf(ans)+"\n");
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

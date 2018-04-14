import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N;
		private int[][] map;
		private boolean[] visited;
		private final int INF = 987654321;
		private int ans = INF;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N][N];
			visited = new boolean[N];
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
		
		private void solve(int idx, int cnt) {
			if(cnt == N/2) {
				int a=0;
				int b=0;
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(visited[i] && visited[j]) {
							a += map[i][j];
						} else if(!visited[i] && !visited[j]) {
							b += map[i][j];
						}
					}
				}
				int sum = Math.abs(a-b);
				ans = ans > sum ? sum : ans;
				return;
			}
			
			for(int i=idx+1; i<N; i++) {
				visited[i] = true;
				solve(i, cnt+1);
				visited[i] = false;
			}
		}
		
		public void run() throws IOException {
			input();
			visited[0] = true;
			solve(0, 1);
			bw.write(String.valueOf(ans)+"\n");
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

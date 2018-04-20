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
		private boolean[] visited;
		private int[] ans;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			visited = new boolean[N+1];
			ans = new int[N+1];
		}

		private void dfs(int depth) {
			if(depth == M+1) {
				for(int i=1; i<=M; i++) {
					System.out.print(ans[i] + " ");
				}
				System.out.println();
				return;
			}
			
			for(int i=1; i<=N; i++) {
				if(visited[i]) continue;
				visited[i] = true;
				ans[depth] = i;
				dfs(depth+1);
				visited[i] = false;
			}
		}
		
		public void run() throws IOException {
			input();
			dfs(1);
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

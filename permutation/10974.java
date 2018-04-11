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
		private boolean[] visited;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			visited = new boolean[N+1];
		}
		
		private void permutation(String str, int idx) {
			if(str.length() == N) {
				int len = str.length();
				for(int i=0; i<len; i++) {
					System.out.print(str.charAt(i) + " ");
				}
				System.out.println();
				return;
			}
			
			for(int i=1; i<=N; i++) {
				if(visited[i]) continue;
				visited[i] = true;
				permutation(str + i, i);
				visited[i] = false;
			}
		}
		
		public void run() throws IOException {
			input();
			for(int i=1; i<=N; i++) {
				visited[i] = true;
				permutation(i+"", i);
				visited[i] = false;
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

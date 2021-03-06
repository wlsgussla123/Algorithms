package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M;
		private int[] arr;
		private int[] res;
		private boolean[] visited;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			arr = new int[N];
			res = new int[N];
			visited = new boolean[N];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			
			st = getStringTokenizer();
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
		}
		
		private void solve(int idx, int depth) throws IOException {
			if(depth == M) {
				for(int i=0; i<M; i++) {
					bw.write(String.valueOf(res[i] + " "));
				}
				bw.write("\n");
				return;
			}
			
			for(int i=0; i<N; i++) {
				res[depth] = arr[i];
				solve(idx, depth+1);
			}
		}
		
		public void run() throws IOException {
			input();
			for(int i=0; i<N; i++) {
				res[0] = arr[i];
				solve(i,1);
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

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
		private int[] res;
		
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
			res = new int[M+1];
		}
		
		private void permutation(int depth, int prev) throws IOException {
			if(depth > M) {
				for(int i=1; i<=M; i++) {
					bw.write(String.valueOf(res[i] + " "));
				}
				bw.write("\n");
				return; 
			}
			
			for(int i=prev; i<=N; i++) {
				res[depth] = i;
				permutation(depth+1, i);
			}
		}
		
		public void run() throws IOException {
			input();
			permutation(1,1);
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

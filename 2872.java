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
		private int[] arr;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			arr = new int[N+1];
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				arr[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		private void solution() throws IOException {
			int here = N;
			for(int i=N; i>=1; i--) {
				if(here == arr[i]) here--;
			}
			bw.write(String.valueOf(here));
		}
		
		public void run() throws IOException {
			input();
			solution();
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

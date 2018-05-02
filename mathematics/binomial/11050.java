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
		private int N,K;
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
		}
		
		private int bino(int n, int k) {
			if(k==0 || k==n) {
				return 1;
			}
			
			return bino(n-1, k-1) + bino(n-1, k);
		}
		
		public void run() throws IOException {
			input();
			bw.write(String.valueOf(bino(N, K)));
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

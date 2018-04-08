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
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
		}
		
		private void solution(int N, int from, int by, int to) {
			if(N==1) {
				System.out.println(from + " " + to);
				return;
			}
			
			solution(N-1, from, to, by);
			System.out.println(from + " " + to);
			solution(N-1, by, from, to);
		}
		
		public void run() throws IOException {
			input();
			System.out.println(String.valueOf((int)Math.pow(2, N)-1));
			solution(N, 1, 2, 3);
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

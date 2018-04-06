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
		private int N,K;
		private int[] coin;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			coin = new int[N+5];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			init();
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				coin[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		private void solution() throws IOException {
			int cnt = 0;
			int idx = N;
			while(K>=1) {
				if(idx==0) break;
				if(K >= coin[idx]) {
					K -= coin[idx];
					cnt++;
				} else {
					idx--;
				}
			}
			bw.write(String.valueOf(cnt));
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

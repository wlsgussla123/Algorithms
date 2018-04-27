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
		private int N;
		private int[] arr;
		private final int MAX = 1000;
		private int[] p = new int[MAX+1];
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			arr = new int[N];
			
			st = getStringTokenizer();
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		private void eratos() {
			p[0] = -1;
			p[1] = -1;
			
			for(int i=2; i<=MAX; i++) {
				if(p[i] == -1) continue;
				for(int j=i+i; j<=MAX; j+=i) {
					p[j] = -1;
				}
			}
		}
		
		private void solve() throws IOException {
			eratos();
			int cnt = N;
			for(int i=0; i<N; i++) {
				if(p[arr[i]] == -1) {
					cnt--;
				}
			}
			bw.write(String.valueOf(cnt));
		}
		
		public void run() throws IOException {
			input();
			solve();
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

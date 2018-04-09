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
		private int N,M,K;
		private int ans = 0;
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
		}
		
		private void solution() {
			int cnt = 0;
			while(cnt!=K) { 
				if(N==0 || M==0) break;
				if(N > M*2) {
					N--;
				} else {
					M--;
				}
				cnt++;
			}
			
			while(N>=2 && M>=1) {
				N-=2;
				M--;
				ans++;
			}
		}
		
		public void run() throws IOException {
			input();
			solution();
			bw.write(String.valueOf(ans));
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

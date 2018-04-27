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
		private int A,B,V;
		private long ans = 2000000000;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
		}
		
		private void solve() throws IOException {
			long left = 0;
			long right = 2000000000;
			
			while(left < right) {
				long mid = (left + right)/2;
				long d = (A * mid) - (B * (mid-1));
				if(d >= V) {
					right = mid;
					ans = Math.min(ans, mid);
				} else {
					left = mid + 1;
				}
			}
			
			bw.write(String.valueOf(ans));
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

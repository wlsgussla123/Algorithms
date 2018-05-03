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
		private int[] dp;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			arr = new int[N+1];
			dp = new int[N+1];
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				arr[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		private void solve() throws IOException {
			int cnt = 1;
			for(int i=1; i<=N; i++) {
				dp[i] = 1;
				for(int j=1; j<i; j++) {
					if(arr[i] > arr[j]) {
						if(dp[j] + 1 > dp[i]) {
							dp[i] = dp[j] + 1;
							cnt = cnt > dp[i] ? cnt : dp[i];
						}
					}
				}
			}
			bw.write(String.valueOf(N-cnt));
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

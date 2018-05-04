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
		private int[] coin;
		private int[] dp;
		private final int MAX = 987654321;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			coin = new int[N+5];
			dp = new int[K+5];
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
			
			dp[0] = 0;
			for(int i=1; i<=K; i++) {
				dp[i] = MAX;
			}
		}
		
		private void solve() throws IOException {
			for(int i=1; i<=N; i++) {
				for(int j=coin[i]; j<=K; j++) {
					dp[j] = Math.min(dp[j-coin[i]] + 1, dp[j]);
				}
			}
			
			if(dp[K] == MAX) {
				bw.write("-1");
			} else {
				bw.write(String.valueOf(dp[K]));
			}
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

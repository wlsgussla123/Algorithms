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
		private int[] dp;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}

		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			coin = new int[N+1];
			st = getStringTokenizer();
			for(int i=1; i<=N; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			st = getStringTokenizer();
			K = Integer.parseInt(st.nextToken());
			dp = new int[K+1];
		}
		
		private void solution() throws IOException {
			dp[0] = 1;
			for(int i=1; i<=N; i++) {
				for(int j=coin[i]; j<=K; j++) {
					dp[j] += dp[j-coin[i]];
				}
			}
			bw.write(String.valueOf(dp[K])+"\n");
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				solution();
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

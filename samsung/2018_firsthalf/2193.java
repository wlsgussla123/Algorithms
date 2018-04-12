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
		private long[][] dp;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			dp = new long[N+5][2];
			dp[1][0] = 0;
			dp[1][1] = 1;
		}
		
		private long solution() {
			for(int i=2; i<=N; i++) {
				dp[i][0] = dp[i-1][0] + dp[i-1][1];
				dp[i][1] = dp[i-1][0];
			}
			
			return dp[N][0] + dp[N][1];
		}
		
		public void run() throws IOException {
			input();
			bw.write(String.valueOf(solution()));
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

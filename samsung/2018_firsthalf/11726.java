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
			dp = new int[1005];
		}
		
		private int solution() {
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 3;
			if(N<=3) return dp[N] % 10007;
			
			for(int i=4; i<=N; i++) {
				dp[i] = (dp[i-1] + dp[i-2]) % 10007;
			}
			
			return dp[N] % 10007;
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

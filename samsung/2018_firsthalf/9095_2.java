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
			dp = new int[15];
		}
		
		private int solution() {
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			for(int i=4; i<=N; i++) {
				dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
			}
			
			return dp[N];
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int idx=1; idx<=T; idx++) {
				input();
				bw.write(String.valueOf(solution())+"\n");
			}
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

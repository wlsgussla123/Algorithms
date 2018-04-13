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
		private final int INF = 987654321;
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			dp = new int[100005];
		}
		
		private int solution() throws IOException {
			dp[0] = INF;
			dp[1] = INF;
			dp[2] = 1;
			dp[3] = INF;
			dp[4] = 2;
			dp[5] = 1;
			for(int i=6; i<=N; i++) {
				dp[i] = dp[i-2] + 1;
				dp[i] = Math.min(dp[i], dp[i-5] + 1); 
			}
			
			return dp[N]; 
		}
		
		public void run() throws IOException {
			input();
			bw.write(String.valueOf(solution())+"\n");
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

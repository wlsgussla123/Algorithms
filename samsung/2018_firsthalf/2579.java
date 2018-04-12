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
		private int[] arr;
		private int[][] dp;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			arr = new int[N+1];
			dp = new int[N+1][2];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				arr[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		private int solution() {
			dp[1][0] = arr[1];
			dp[1][1] = arr[1];
			
			for(int i=2; i<=N; i++) {
				dp[i][0] = dp[i-1][1] + arr[i];
				dp[i][1] = Math.max(dp[i-2][0], dp[i-2][1]) + arr[i];
			}
			
			return Math.max(dp[N][0], dp[N][1]);
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

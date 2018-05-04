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
		private int[] arr = new int[1005];
		private int[] dp = new int[1005];
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			st = getStringTokenizer();
			for(int i=1; i<=N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		private void solve() throws IOException {
			int max = 0;
			for(int i=1; i<=N; i++) {
				dp[i] = 1;
				for(int j=1; j<i; j++) {
					if(arr[j] < arr[i]) {
						if(dp[j] + 1 > dp[i]) {
							dp[i] = dp[j] + 1;
						}
					}
				}
				max = max > dp[i] ? max : dp[i];
			}
			
			bw.write(String.valueOf(max));
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

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
		private int[] arr = new int[505];
		private int[] dp = new int[505];
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				int idx = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				arr[idx] = value;
			}
		}
		
		private void solve() throws IOException {
			int max = 0;
			for(int i=1; i<=500; i++) {
				if(arr[i] == 0) continue;
				dp[i] = 1;
				for(int j=1; j<i; j++) {
					if(arr[j] < arr[i]) {
						if(dp[j]+1 > dp[i]) {
							dp[i] = dp[j]+1;
						}
					}
				}
				max = max > dp[i] ? max : dp[i];
			}
			
			bw.write(String.valueOf(N-max));
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

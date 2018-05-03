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
			dp[1] = arr[1];
			
			int idx = 1;
			for(int i=2; i<=N; i++) {
				if(dp[idx] < arr[i]) {
					dp[++idx] = arr[i];
				} else {
					int next = lower_bound(idx, i);
					dp[next] = arr[i];
				}
			}
			
			bw.write(String.valueOf(N-idx));
		}
		
		private int lower_bound(int end, int n) {
			int start = 1;
			while(start < end) {
				int mid = (start + end) / 2;
				if(dp[mid] >= arr[n]) {
					end = mid;
				} else {
					start = mid + 1;
				}
			}
			
			return end;
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

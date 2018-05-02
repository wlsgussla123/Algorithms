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
		private int[] arr = new int[1000001];
		private int[] dp = new int[1000001];
		
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
			
			for(int i=1; i<=N; i++) {
				dp[i] = -1;
			}
		}
		
		private void solve() throws IOException {
			dp[1] = arr[1];
			int idx = 1;
			for(int i=2; i<=N; i++) {
				if(dp[idx] < arr[i]) {
					dp[++idx] = arr[i];
				} else {
					int found = lower_bound(idx, arr[i]);
					dp[found] = arr[i];
				}
			}
			
			bw.write(String.valueOf(idx));
		}
		
		private int lower_bound(int right, int n) {
			int left = 1;
			while(left < right) {
				int mid = (left + right) / 2;
				if(dp[mid] >= n) {
					right = mid;
				} else {
					left = mid + 1; 
				}
			}
			
			return right;
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

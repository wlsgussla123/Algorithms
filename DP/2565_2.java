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
			int idx = 1;
			boolean flag = true;
			for(int i=1; i<=500; i++) {
				if(arr[i] == 0) continue;
				if(flag) {
					dp[idx] = arr[i];
					flag = false;
				}
				
				if(dp[idx] < arr[i]) {
					dp[++idx] = arr[i];
				} else {
					int found = search(idx, arr[i]);
					dp[found] = arr[i];
				}
			}
			
			bw.write(String.valueOf(N-idx));
		}
		
		private int search(int end, int n) {
			int start = 1;
			while(start < end) {
				int mid = (start + end) / 2;
				if(dp[mid] >= n) {
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

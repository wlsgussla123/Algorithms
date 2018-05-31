package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}

	static class Solution {
		private int N;
		private int[] arr;
		private int[] lis;
		
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
			lis = new int[N+1];
			
			st = getStringTokenizer();
			for(int i=1; i<=N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
		}

		private void solution() throws IOException {
			int sum = 0;
			int ans = 0;
			
			for(int i=1; i<=N; i++) {
				sum = 0;
				for(int j=0; j<i; j++) {
					if(arr[i] > arr[j]) {
						if(sum < lis[j]) {
							sum = lis[j];
						}
					}
				}
				lis[i] = sum + arr[i];
				ans = ans > lis[i] ? ans : lis[i];
			}
			
			bw.write(String.valueOf(ans));
		}
		
		public void run() throws IOException {
			input();
			solution();
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

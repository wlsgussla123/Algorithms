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
		private int[] arr = new int[1001];
		private int[] lcs = new int[1001];
		
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
				lcs[i] = -1;
			}
		}
		
		private int solve() {
			int ans = 1;
			for(int i=1; i<=N; i++) {
				lcs[i] = 1;
				for(int j=1; j<=i-1; j++) {
					if(arr[i] > arr[j] && lcs[j] + 1 > lcs[i]) {
						lcs[i] = lcs[j] + 1;
						ans = ans > lcs[i] ? ans : lcs[i];
					}
				}
			}
			
			return ans;
		}
		
		public void run() throws IOException {
			input();
			bw.write(String.valueOf(solve()));
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

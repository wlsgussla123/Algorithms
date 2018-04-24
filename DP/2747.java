package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N;
		private int[] dp = new int[50];
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			dp[0] = 0;
			dp[1] = 1;
		}
		
		private void solve() throws IOException {
			for(int i=2; i<=N; i++) {
				dp[i] = dp[i-1] + dp[i-2];
			}
			
			bw.write(String.valueOf(dp[N]));
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

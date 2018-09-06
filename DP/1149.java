package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[][] arr;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		InputClass.input();
		new Solution().run();
		InputClass.close();
	}
	
	static class InputClass {
		public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
		public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
		private static StringTokenizer st = null;
		
		public static void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			arr = new int[N+1][3];
			dp = new int[N+1][3];
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				for(int j=0; j<3; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		public static void close() throws IOException {
			BR.close();
			BW.close();
		}
		
		public static StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(InputClass.BR.readLine(), " ");
		}
	}
	
	static class Solution {
		public void run() throws IOException {
			for(int i=1; i<=N; i++) {
				dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[i][0];
				dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + arr[i][1];
				dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i][2];
			}
			
			InputClass.BW.write(String.valueOf(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2])));
		}
	}
}

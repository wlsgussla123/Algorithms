package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int T;
	private static int[] N;

	public static void main(String[] args) throws IOException {
		InputClass.input();
		new Solution().run(N);
		InputClass.close();
	}
	
	static class InputClass {
		public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
		public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
		private static StringTokenizer st = null;
		
		public static void input() throws IOException {
			st = getStringTokenizer();
			T = Integer.parseInt(st.nextToken());
			N = new int[T];
			
			int idx = 0;
			while(idx < T) {
				st = getStringTokenizer();
				N[idx++] = Integer.parseInt(st.nextToken());
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
		public void run(int[] N) throws IOException {
			int len = N.length;
			int idx = 0;
			
			while(idx < len) {
				InputClass.BW.write(square(N[idx++]) + "\n");
			}
		}
		
		public String square(int n) {
			String squareStr = String.valueOf(n * n);
			String convertToN = String.valueOf(n);
			
			int idx1 = squareStr.length() - 1;
			int idx2 = convertToN.length() - 1;
			int len = convertToN.length();
			for(int i=0; i<len; i++) {
				if(squareStr.charAt(idx1--) != convertToN.charAt(idx2--)) {
					return "NO";
				}
			}
			
			return "YES";
		}
	}
}

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static String str = null;
	private static int ans = 0;

	public static void main(String[] args) throws IOException {
		InputClass.input();
		new Solution().run();
		InputClass.close();
	}

	static class Solution {
		public void run() throws IOException {
			str.chars()
					.forEach(i -> {
						char c = (char) i;
						if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
							ans++;
						}
					});
			
			InputClass.BW.write(ans+"\n");
		}
	}
	
	static class InputClass {
		public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
		public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
		private static StringTokenizer st = null;
		
		public static void input() throws IOException {
			st = getStringTokenizer();
			str = st.nextToken();
		}
		
		public static void close() throws IOException {
			BR.close();
			BW.close();
		}
		
		public static StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(InputClass.BR.readLine(), " ");
		}
	}
}

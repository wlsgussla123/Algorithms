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
	private static String[] str;

	public static void main(String[] args) throws IOException {
		InputClass.input();
		InputClass.BW.write(new Solution().run());
		InputClass.close();
	}
	
	static class InputClass {
		public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
		public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
		private static StringTokenizer st = null;
		
		public static void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			str = new String[N];
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				str[i] = st.nextToken();
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
		public String run() {
			String[] origin = Arrays.stream(str).toArray(String[]::new);
			
			if(isIncreasing(origin)) {
				return "INCREASING";
			} else if(isDecreasing(origin)) {
				return "DECREASING";
			} else {
				return "NEITHER";
			}
		}
		
		private boolean isIncreasing(String[] origin) {
			Arrays.sort(str);
			int len = str.length;
			for(int i=0; i<len; i++) {
				if(!str[i].equals(origin[i]))
					return false;
			}
			
			return true;
		}
		
		private boolean isDecreasing(String[] origin) {
			Arrays.sort(str, Collections.reverseOrder());
			int len = str.length;
			for(int i=0; i<len; i++) {
				if(!str[i].equals(origin[i]))
					return false;
			}

			return true;
		}
	}
}

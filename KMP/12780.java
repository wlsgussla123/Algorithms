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
		private String T,P;
		private int[] pi;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			T = st.nextToken();
			st = getStringTokenizer();
			P = st.nextToken();
			pi = new int[P.length()];
		}
		
		private void getPi(String pattern) {
			pi[0] = 0;
			char[] p = pattern.toCharArray();
			int len = pattern.length();
			int j = 0;
			
			for(int i=1; i<len; i++) {
				while(j>0 && p[i] != p[j]) {
					j = pi[j - 1];
				}
				
				if(p[i] == p[j]) {
					pi[i] = ++j;
				}
			}
		}
		
		private void solution(String text, String pattern) throws IOException {
			int len_t = text.length();
			int len_p = pattern.length();
			char[] t = text.toCharArray();
			char[] p = pattern.toCharArray();
			int j = 0;
			
			int ans = 0;
			for(int i=0; i<len_t; i++) {
				while(j>0 && t[i] != p[j]) {
					j = pi[j - 1];
				}
				
				if(t[i] == p[j]) {
					j++;
					if(len_p == j) {
						ans++;
						j = pi[j - 1];
					}
				}
			}
			bw.write(String.valueOf(ans));
		}
		
		public void run() throws IOException {
			input();
			getPi(P);
			solution(T,P);
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

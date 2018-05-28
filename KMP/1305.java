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
		private int L,N;
		private String str;
		private int[] pi;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), "");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			L = Integer.parseInt(st.nextToken());
			st = getStringTokenizer();
			str = st.nextToken();
			N = str.length();
			pi = new int[N];
		}
		
		private void solution() {
			pi[0] = 0;
			char[] p = str.toCharArray();
			int j = 0;
			
			for(int i=1; i<N; i++) {
				while(j>0 && p[i] != p[j]) {
					j = pi[j - 1];
				}
				
				if(p[i] == p[j]) {
					pi[i] = ++j;
				}
			}
			
			System.out.println(L-pi[L-1]);
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

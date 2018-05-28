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
		private String text;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), "");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			text = st.nextToken();
		}
		
		private void solution(String text) {
			char[] cipher = text.toCharArray();
			int len = cipher.length;
			for(int i=0; i<len; i++) {
				if(cipher[i] >= 'a' && cipher[i] <= 'z') {
					cipher[i] = (char)(cipher[i] + 13);
					if(cipher[i] > 'z') {
						cipher[i] = (char)((cipher[i] - 'z' - 1 ) + 'a');
					}
				} else if(cipher[i] >= 'A' && cipher[i] <= 'Z') {
					cipher[i] = (char)(cipher[i] + 13);
					if(cipher[i] > 'Z') {
						cipher[i] = (char)((cipher[i] - 'Z' - 1) + 'A');
					}
				}
			}
			
			for(int i=0; i<len; i++) {
				System.out.print(cipher[i]);
			}
		}

		public void run() throws IOException {
			input();
			solution(text);
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

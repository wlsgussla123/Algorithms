package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}

	static class Solution {

		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		public int solution(String A, String B) {
			char[] alice = A.toCharArray();
			char[] bob = B.toCharArray();
			int len = alice.length;
			
			int cnt = 0;
			for(int i=0; i<len; i++) {
				if(alice[i] == bob[i]) continue;
				
				if(alice[i] == 'A') {
					cnt++;
				} else if(alice[i] == 'K') {
					if(bob[i] != 'A' && bob[i] != 'K') {
						cnt++;
					}
				} else if(alice[i] == 'Q') {
					if(bob[i] != 'A' && bob[i] != 'K' && bob[i] != 'Q') {
						cnt++;
					}
				} else if(alice[i] == 'J') {
					if(bob[i] != 'A' && bob[i] != 'K' && bob[i] != 'Q' && bob[i] != 'J') {
						cnt++;
					}
				} else if(alice[i] == 'T') {
					if(bob[i] != 'A' && bob[i] != 'K' && bob[i] != 'Q' && bob[i] != 'J' && bob[i] != 'T') {
						cnt++;
					}
				} else {
					if(bob[i] >= '2' && bob[i] <= '9') {
						int na = alice[i] - '0';
						int nb = bob[i] - '0';
						
						if(na > nb) cnt++;
					}
				}
			}
			
			return cnt;
		}

		public void run() throws IOException {
			bw.write(String.valueOf(solution("23A84Q", "K2Q25J")));
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

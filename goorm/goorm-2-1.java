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
		private int n,d,k,j;
		private boolean[] isFail;
		private int failIdx = 1;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			isFail = new boolean[n+1];
		}
		
		private void solution() throws IOException {
			int idx = 1;
			int tmp = 0;
			while(idx < n) {
				tmp = 0;
				while(tmp < k) {
					if(isFail[(failIdx % n)+1]) {
						failIdx = (failIdx % n) + 1;
						continue;
					}
					failIdx = (failIdx % n) + 1;
					tmp++;
				}
				isFail[failIdx] = true;
				k = k+j;
				idx++;
			}
			
			for(int i=1; i<=n; i++) {
				if(!isFail[i]) bw.write(String.valueOf(i)+"\n");
			}
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

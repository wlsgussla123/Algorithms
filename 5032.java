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
		private int e,f,c;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			e = Integer.parseInt(st.nextToken());
			f = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
		}
		
		private void solution() throws IOException {
			int ans = 0;
			int cnt = e+f;
			
			while(true) {
				if(cnt < c)
					break;
				ans += cnt/c;
				cnt = cnt%c + cnt/c;
			}
			
			bw.write(String.valueOf(ans));
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

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}

	static class Solution {
		private int N, M;
		private HashMap<Integer, Integer> map = new HashMap();
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			st = getStringTokenizer();
			for(int i=0; i<N; i++) {
				int n = Integer.parseInt(st.nextToken());
				if(map.get(n) != null) {
					int cnt = map.get(n);
					map.remove(n);
					map.put(n, cnt+1);
				} else {
					map.put(n, 1);
				}
			}
			
			st = getStringTokenizer();
			M = Integer.parseInt(st.nextToken());
			st = getStringTokenizer();
			for(int i=0; i<M; i++) {
				int key = Integer.parseInt(st.nextToken());
				Integer ans = map.get(key);
				if(ans == null) {
					bw.write("0 ");
				} else {
					bw.write(String.valueOf(ans + " "));
				}
			}
		}

		public void run() throws IOException {
			input();
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

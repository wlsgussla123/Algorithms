package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}

	static class Solution {
		private int N;
		private ArrayList<Integer> list = new ArrayList();
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			
			int input = 0;
			while(true) {
				st = getStringTokenizer();
				input = Integer.parseInt(st.nextToken());
				if(input == 0) break;
				list.add(input);
			}
		}
		
		private void solution() {
			for(Integer num : list) {
				if(num%N == 0) {
					System.out.println(num + " is a multiple of " + N + ".");
				} else {
					System.out.println(num + " is NOT a multiple of " + N + ".");
				}
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

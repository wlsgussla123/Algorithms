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
		private int N,R,C;
		private int[][] map;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N+1][N+1];
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				for(int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		private void solution() throws IOException {
			for(int i=1; i<=N; i++) {
				if(map[R][i] > map[R][C]) {
					System.out.println("ANGRY");
					return;
				}
			}
			
			for(int i=1; i<=N; i++) {
				if(map[i][C] > map[R][C]) {
					System.out.println("ANGRY");
					return;
				}
			}
			
			System.out.println("HAPPY");
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

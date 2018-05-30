package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}

	static class Solution {
		private int[][] map = new int[101][101];
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}

		private void input() throws IOException {
			int x1,y1,x2,y2;
			for(int i=0; i<4; i++) {
				st = getStringTokenizer();
				x1 = Integer.parseInt(st.nextToken());
				y1 = Integer.parseInt(st.nextToken());
				x2 = Integer.parseInt(st.nextToken());
				y2 = Integer.parseInt(st.nextToken());
			
				for(int x=x1; x<x2; x++) {
					for(int y=y1; y<y2; y++) {
						map[x][y] = 1;
					}
				}
			}
		}
		
		private void solution() throws IOException {
			int cnt = 0;
			for(int i=1; i<=100; i++) {
				for(int j=1; j<=100; j++) {
					if(map[i][j] == 1) {
						cnt++;
					}
				}
			}
			System.out.println(cnt);
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

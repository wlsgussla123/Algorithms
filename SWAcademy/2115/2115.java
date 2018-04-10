package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M,C;
		private int[][] map;
		private int temp;
		private int chkX, chkY;
		private int first, second;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N][N];
			chkX = 0;
			chkY = 0;
			first = 0;
			second = 0;
			temp = 0;
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		private void collect(int x, int y, int sum, int psum, int ret) {
			if(sum<=C) {
				if(temp < psum) {
					temp = psum;
					chkX = x;
					chkY = y-1;
				}
			}
			
			if(ret==0 || y==N)
				return;
			
			collect(x, y+1, sum + map[x][y], psum + map[x][y] * map[x][y], ret-1);
			collect(x, y+1, sum, psum, ret-1);
		}
		
		private void solution() {
			temp = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					collect(i, j, 0, 0, M);
				}
			}
			
			first = temp;
			temp = 0;
			int passX = chkX;
			int passY = chkY;
			for(int i=0; i<N; i++) {
				if(passX == i) {
					for(int j=passY+1; j<N; j++) {
						collect(i, j, 0, 0, M);
					}
				} else {
					for(int j=0; j<N; j++) {
						collect(i, j, 0, 0, M);
					}
				}
			}
			second = temp;
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				solution();
				bw.write(String.valueOf("#" + i + " " + (first+second))+"\n");
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

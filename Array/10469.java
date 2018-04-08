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
		private final int N = 8;
		private char[][] map = new char[N][N];
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				char[] input = st.nextToken().toCharArray();
				for(int j=0; j<N; j++) {
					map[i][j] = input[j];
				}
			}
		}
		
		private boolean checkRow() {
			int cnt = 0;
			for(int i=0; i<N; i++) {
				cnt = 0;
				for(int j=0; j<N; j++) {
					if(map[i][j] == '*') {
						cnt++;
					}
				}
				if(cnt != 1) return false;
			}
			
			return true;
		}
		
		private boolean checkCol() {
			int cnt = 0;
			for(int j=0; j<N; j++) {
				cnt = 0;
				for(int i=0; i<N; i++) {
					if(map[i][j] == '*') {
						cnt++;
					}
				}
				if(cnt != 1) return false;
			}
			return true;
		}
		
		private boolean checkDiagonal() {
			int cnt = 0;
			for(int i=0; i<N; i++) {
				cnt = 0;
				for(int j=0; j<N; j++) {
					int x = i;
					int y = j;
					cnt = 0;
					while(true) {
						if(!checkArea(x, y)) break;
						if(map[x][y] == '*') {
							cnt++;
						}
						x = x+1;
						y = y+1;
					}
					if(cnt > 1) return false;
				}
			}
			
			for(int i=0; i<N; i++) {
				cnt = 0;
				for(int j=N-1; j>=0; j--) {
					int x = i;
					int y = j;
					cnt = 0;
					while(true) {
						if(!checkArea(x, y)) break;
						if(map[x][y] == '*') {
							cnt++;
						}
						x = x+1;
						y = y-1;
					}
					if(cnt > 1) return false;
				}
			}
			
			return true;
		}
		
		private boolean checkCount() {
			int cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == '*') cnt++;
				}
			}
			
			if(cnt == 8) return true;
			else return false;
		}
		
		private boolean checkArea(int x, int y) {
			return x>=0 && x<N && y>=0 && y<N;
		}
		
		private boolean checkQueen() {
			return checkRow() && checkCol() && checkDiagonal() && checkCount();
		}
		
		public void run() throws IOException {
			input();
			if(checkQueen()) bw.write("valid");
			else bw.write("invalid");
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

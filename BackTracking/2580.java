package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Position {
	int x;
	int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int[][] map;
		private int N = 9;
		private ArrayList<Position> list = new ArrayList();
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}

		private void input() throws IOException {
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 0) {
						list.add(new Position(i, j));
					}
				}
			}
		}
		
		private boolean checkRow(int r, int k) {
			for(int c=0; c<N; c++) {
				if(map[r][c] == k) return false;
			}
			return true;
		}
		
		private boolean checkCol(int c, int k) {
			for(int r=0; r<N; r++) {
				if(map[r][c] == k) return false;
			}
			return true;
		}
		
		private boolean checkRec(int r, int c, int k) {
			int cr = (r/3) * 3;
			int cc = (c/3) * 3;
			
			for(int i=cr; i<cr+3; i++) {
				for(int j=cc; j<cc+3; j++) {
					if(map[i][j] == k) return false;
				}
			}
			
			return true;
		}
		
		private boolean isPromising(int r, int c, int k) {
			if(!checkRow(r, k) || !checkCol(c, k) || !checkRec(r, c, k))
				return false;
			return true;
		}
		
		private void backtracking(int idx) {
			if(idx==list.size()) {
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						System.out.print(map[i][j] + " ");
					}
					System.out.println();
				}
				System.exit(0);
			}
			
			Position p = list.get(idx);
			
			for(int k=1; k<=9; k++) {
				if(isPromising(p.x, p.y, k)) {
					map[p.x][p.y] = k;
					backtracking(idx+1);
					map[p.x][p.y] = 0;
				}
			}
		}
		
		
		public void run() throws IOException {
			input();
			backtracking(0);
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

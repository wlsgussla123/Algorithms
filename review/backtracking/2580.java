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
		private int[][] map = new int[9][9];
		private ArrayList<Position> list = new ArrayList();
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			for(int i=0; i<9; i++) {
				st = getStringTokenizer();
				for(int j=0; j<9; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 0) {
						list.add(new Position(i, j));
					}
				}
			}
		}
		
		private boolean checkRow(int x, int num) {
			for(int i=0; i<9; i++) {
				if(map[x][i] == num) return false;
			}
			return true;
		}
		
		private boolean checkCol(int y, int num) {
			for(int i=0; i<9; i++) {
				if(map[i][y] == num) return false;
			}
			return true;
		}
		
		private boolean checkArea(int x, int y, int num) {
			x = x/3 * 3;
			y = y/3 * 3;
			
			for(int i=x; i<x+3; i++) {
				for(int j=y; j<y+3; j++) {
					if(map[i][j] == num) return false;
				}
			}
			return true;
		}
		
		private boolean checkNumber(int x, int y, int num) {
			if(!checkArea(x, y, num) || !checkRow(x, num) || !checkCol(y, num)) return false;
			return true;
		}
		
		private void solve(int idx) {
			if(idx == list.size()) {
				for(int i=0; i<9; i++) {
					for(int j=0; j<9; j++) {
						System.out.print(map[i][j] + " ");
					}
					System.out.println();
				}
				return;
			}
			
			Position pos = list.get(idx);
			for(int i=1; i<=9; i++) {
				if(checkNumber(pos.x, pos.y, i)) {
					map[pos.x][pos.y] = i;
					solve(idx+1);
					map[pos.x][pos.y] = 0;
				}
			}
		}
		
		public void run() throws IOException {
			input();
			solve(0);
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

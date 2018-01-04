package algo;

import java.io.IOException;
import java.util.Scanner;

class Task {
	private int N;
	private char[][] map;
	private boolean[][] visited;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	
	private final Scanner sc = new Scanner(System.in);
	
	private void init() {
		map = new char[N][N];
		visited = new boolean[N][N];
	}

	private void input() throws IOException {
		N = sc.nextInt();
		init();
		
		for(int i=0; i<N; i++) {
			String str = sc.next();
			map[i] = str.toCharArray();
		}
	}
	
	private void colorNormal(int row, int col, char color, int area) {
		for(int i=0; i<4; i++) {
			int x = row + dirs[i][0];
			int y = col + dirs[i][1];
			
			if(x>=0 && x<N && y>=0 && y<N && !visited[x][y]) {
				if(color == map[x][y]) {
					visited[x][y] = true;
					colorNormal(x, y, map[x][y], area);					
				}
			}
		}
	}

	public void run() throws IOException {
		input();
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					colorNormal(i,j,map[i][j],++cnt);
				}
			}
		}
		System.out.print(cnt + " ");
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				visited[i][j] = false;
				if(map[i][j] == 'G') map[i][j] = 'R';
			}
		}

		cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					colorNormal(i,j,map[i][j],++cnt);
				}
			}
		}
		System.out.println(cnt);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

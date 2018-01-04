package algo;

import java.io.IOException;
import java.util.Scanner;

class Task {
	private int N;
	private char[][] map;
	private boolean[][] visited;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	
	private int cnt = 0;
	
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
	
	// DFS를 할 수 있는 곳까지는 전부 방문하여 visit처리를 한다. 나머지 방문하지 않은 곳은 바깥쪽에서 다시 카운팅하며 다시 들어올 것.
	private void color(int row, int col, char color, int area, boolean weakness) {
		for(int i=0; i<4; i++) {
			int x = row + dirs[i][0];
			int y = col + dirs[i][1];
			
			if(x>=0 && x<N && y>=0 && y<N && !visited[x][y]) {
				if(color == map[x][y]) {
					visited[x][y] = true;
					color(x, y, map[x][y], area, weakness);		
				} else {
					if(weakness) {
						if(color != 'B' && map[x][y] != 'B') {
							visited[x][y] = true;
							color(x, y, map[x][y], area, weakness);		
						}
					}
				}
			}
		}
	}
	
	private void clear() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				visited[i][j] = false;
			}
		}
		cnt = 0;
	}

	// normal에 대해서 DFS를 한다. R,G,B를 다른 구역으로 보고, 각 구역마다 CNT를 통하여 카운팅
	private void normal() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					color(i, j, map[i][j], ++cnt, false);
				}
			}
		}
		System.out.print(cnt + " ");		
	}
	
	// weakness에 대해서 DFS를 한다. R과 G는 같은 구역으로 봐서 깊이탐색을 실시하고, 각 구열마다 CNT를 통하여 카운팅
	private void weakness() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					color(i, j, map[i][j], ++cnt, true);
				}
			}
		}
		System.out.println(cnt);
	}

	public void run() throws IOException {
		input();
		normal();	// 정상
		clear(); // 방문 배열 지워주고
		weakness();	// 적록색약
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

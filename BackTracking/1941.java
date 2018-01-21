package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int c = 0;
	private char[][] map;
	private boolean[][] check;
	private boolean[] visited;
	private int answer = 0;
	private int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[5][5];
		check = new boolean[5][5];
		visited = new boolean[25];
	}
	
	private void input() throws IOException {
		init();
		for(int i=0; i<5; i++) {
			st = getStringTokenizer();
			String str = st.nextToken();
			char[] input = str.toCharArray();
			for(int j=0; j<5; j++) {
				map[i][j] = input[j];
			}
		}
	}
	
	private void isConnected(int x, int y, boolean[][] conn) {
		if(c == 7) {
			++answer;
		} else {
			for(int i=0; i<4; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
			
				if(0 <= ny && ny < 5 && 0 <= nx && nx < 5 && !conn[nx][ny] && check[nx][ny]) {
					conn[nx][ny] = true;
					++c;
					isConnected(nx,ny,conn);
				}
			}
		}
	}
	
	private void find() {
		for(int i=0; i<25; i++) {
			if(visited[i]) {
				int x = i/5;
				int y = i%5;
				boolean[][] conn = new boolean[5][5];
				conn[x][y] = true;
				c = 1;
				isConnected(x,y,conn);
				return;
			}
		}				
	}
	
	private void backtracking(int index, int cnt, int s) {
		if(map[index/5][index%5] == 'S') {
			++s;
		}
		check[index/5][index%5] = true;
	
		if(cnt == 7) {
			if(s>=4) {
				find();
			}
		} else {
			for(int i=index+1; i<25; i++) {
				if(!visited[i]) {
					visited[index] = true;
					backtracking(i, cnt+1, s);
					visited[index] = false;
				}
			}
		}
		check[index/5][index%5] = false;
	}
	
	public void run() throws IOException {
		input();
		for(int i=0; i<25; i++) {
			check = new boolean[25][25];
			visited[i] = true;
			backtracking(i, 1, 0);
			visited[i] = false;
		}
		System.out.println(answer);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int[][] dirs = {{1,2}, {-1,2}, {-1, -2}, {1, -2}, {-2, 1}, {-2, -1}, {2, 1}, {2, -1}}; 
	private boolean[][] visited;
	private int answer = 987654321;
	
	private int T, I; // 테스트 케이스, 변의 길이
	private int curRow, curCol, arriveRow, arriveCol;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		visited = new boolean[I][I];
		for(int i=0; i<I; i++) {
			for(int j=0; j<I; j++) {
				visited[i][j] = false;
			}
		}
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		I = Integer.parseInt(st.nextToken());
		
		init();
		
		for(int i=0; i<2; i++) {
			st = getStringTokenizer();
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			
			if(i==0) {
				curRow = row;
				curCol = col;
			} else {
				arriveRow = row;
				arriveCol = col;
			}
		}
	}
	
	private class Data {
		int row;
		int col;
		int cnt;
		
		public Data(int row, int col, int cnt) {
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}
	}
	
	private void bfs() {
		Queue<Data> q = new LinkedList<Data>();
		q.add(new Data(curRow, curCol, 0));
		
		while(!q.isEmpty()) {
			Data data = q.poll();
			int x = data.row;
			int y = data.col;
			int cnt = data.cnt;
			
			visited[x][y] = true;
			if(x == arriveRow && y == arriveCol) {
				answer = cnt;
				break;
			}
			
			for(int i=0; i<8; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				
				if(nx>=0 && nx<I && ny>=0 && ny<I && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new Data(nx, ny, cnt+1));
				}
			}
		}
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		T = Integer.parseInt(st.nextToken());
		
		while(T>0) {
			input();
			bfs();
			System.out.println(answer);
			
			T--;
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

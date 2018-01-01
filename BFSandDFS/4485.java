package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Task {
	private int[][] map;
	private boolean[][] visit;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}}; // 동서남북
	private int N = 1;
	private int T = 1;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private void init() {
		map = new int[N+1][N+1];
		visit = new boolean[N+1][N+1];
		
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				visit[i][j] = false;
			}
		}
	}
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		init();
		
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private class Data implements Comparable<Data> {
		int row;
		int col;
		int weight;
		
		public Data(int row, int col, int weight) {
			this.row = row;
			this.col = col;
			this.weight = weight;
		}

		@Override
		public int compareTo(Data o) {
			return this.weight - o.weight;
		}
	}
	
	private void bfs(int row, int col) {
		PriorityQueue<Data> pq = new PriorityQueue<>();
		pq.offer(new Data(row, col, map[row][col]));
		
		while(!pq.isEmpty()) {
			Data data = pq.poll();
			int x = data.row;
			int y = data.col;
			int weight = data.weight;
			
			if(x == N && y == N) {
				System.out.println("Problem" + " " + T + ": " + weight);
				break;
			}
			
			visit[x][y] = true;
			
			for(int i=0; i<4; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				
				if(nx>=1 && nx<=N && ny>=1 && ny<=N && !visit[nx][ny]) {
					pq.offer(new Data(nx, ny, weight + map[nx][ny]));
				}
			}
		}
	}
	
	public void run() throws IOException {
		while(N!=0) {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			if(N==0) break;
			
			input();
			bfs(1,1);
			T++;
		}
		
		br.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

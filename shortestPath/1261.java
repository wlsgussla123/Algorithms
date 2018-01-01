package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Task {
	private int[][] map;
	private boolean[][] visit;
	private int answer = 987654321;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}}; // 동서남북
	private int N,M; // 행, 열
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private void init() {
		map = new int[N+1][M+1];
		visit = new boolean[N+1][M+1];
		
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=M; j++) {
				visit[i][j] = false;
			}
		}
	}
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		init();
		
		String str;
		char[] numbers;
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			
			str = st.nextToken();
			numbers = new char[str.length()];
			numbers = str.toCharArray();
			
			for(int j=1; j<=M; j++) {
				map[i][j] = numbers[j-1] - '0';
			}
		}
	}
	
	class Data implements Comparable<Data> {
		int row;
		int col;
		int cnt;
		
		public Data(int row, int col, int cnt) {
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Data o) {
			// TODO Auto-generated method stub
			return this.cnt - o.cnt;
		}
	}

	private void bfs(int row, int col) {
		PriorityQueue<Data> pq = new PriorityQueue<>();
		pq.offer(new Data(row, col, map[row][col]));
		visit[row][col] = true;
		
		while(!pq.isEmpty()) {
			Data data = pq.poll();
			int x = data.row;
			int y = data.col;
			int cnt = data.cnt;
			
			if(x == N && y == M) {
				answer = cnt;
				break;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				
				if(nx >= 1 && nx <= N && ny >= 1 && ny <= M && !visit[nx][ny]) {
					visit[nx][ny] = true;
					int nw = cnt + (map[nx][ny] == 1 ? 1 : 0);
					pq.add(new Data(nx, ny, nw));
				}
			}
		}
	}
	
	private void process() {
		bfs(1,1);
	}

	private void answer() {
		System.out.println(answer);
	}
	
	public void run() throws IOException {
		input();
		process();
		answer();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

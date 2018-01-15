package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[][] map;
	private boolean[][] visited;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}}; // 동서남북
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			String number = st.nextToken();
			for(int j=1; j<=M; j++) {
				map[i][j] = number.charAt(j-1) - '0';
			}
		}
	}
	
	private void bfs(int row, int col, int cnt) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {row, col, cnt};
		q.add(pos);
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int c = cur[2];
			
			if(x == N && y == M) {
				System.out.println(c);
				break;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				
				if(nx>=1 && nx<=N && ny>=1 && ny<=M && map[nx][ny] != 0 && !visited[nx][ny]) {
					int[] next = {nx, ny, c+1};
					visited[nx][ny] = true;
					q.add(next);
				}
			}
		}
	}

	public void run() throws IOException {
		input();
		bfs(1,1,1);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

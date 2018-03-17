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
	private boolean[][][] visited;
	private int[][][] dist;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	private int answer = -1;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1][2];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				for(int k=0; k<2; k++) {
					visited[i][j][k] = false;
				}
			}
		}
		dist = new int[N+1][M+1][2];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			String str = st.nextToken();
			char[] input = str.toCharArray();
			for(int j=1; j<=M; j++) {
				map[i][j] = input[j-1] - '0';
			}
		}
	}
	
	private void bfs(int row, int col, int cnt) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {1,1,1}; // 좌표, 몇 개를 부실 수 있는지
		q.add(pos);
		dist[1][1][1] = 1;
		visited[1][1][1] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int isBreak = cur[2];
			
			if(x == N && y == M) break;
			for(int i=0; i<4; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				
				if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny][isBreak]) {
					if(map[nx][ny] == 0) {
						int[] next = {nx,ny,isBreak};
						dist[nx][ny][isBreak] = dist[x][y][isBreak] + 1;
						q.add(next);
					} else {
						if(isBreak == 1) {
							int[] next = {ny, nx, 0};
							dist[nx][ny][0] = dist[x][y][isBreak] + 1;
							q.add(next);
						}
					}
				}
			}
		}

		if(dist[N][M][1] == 0 && dist[N][M][0] == 0) {
			answer = -1;
		} else if(dist[N][M][0] == 0) {
			answer = dist[N][M][1];
		} else if(dist[N][M][1] == 0) {
			answer = dist[N][M][0];
		} else {
			answer = Math.min(dist[N][M][0], dist[N][M][1]);
		}
	}
	
	public void run() throws IOException {
		input();
		bfs(1,1,1);
		
		System.out.println(answer);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

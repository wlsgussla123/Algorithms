package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,M,K;
	private char[][] map;
	private boolean[][] visited;
	private int sx,sy;
	private int answer = 0;
	private int ability = 1;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[N+1][M+1];
		visited = new boolean[N+1][M+1];
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=M; j++) {
				map[i][j] = input[j-1];
				if(input[j-1] == 'S') {
					sx = i;
					sy = j;
				}
			}
		}
	}
	
	private void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {r,c,0};
		visited[r][c] = true;
		q.add(pos);
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int cnt = cur[2];
			if(map[x][y]-'0' == ability) {
				answer += cnt;
				ability++;
				map[x][y] = '.';
				sx = x;
				sy = y;
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				
				if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny]) {
					if(map[nx][ny] == 'X') continue;
					int[] next = {nx,ny,cnt+1};
					visited[nx][ny] = true;
					q.add(next);
				}
			}
		}
	}

	private void process() {
		while(K-->0) {
			bfs(sx,sy);
			visited = new boolean[N+1][M+1];
		}
		System.out.println(answer);
	}
	
	public void run() throws IOException {
		input();
		process();
	}
	
	private void print() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

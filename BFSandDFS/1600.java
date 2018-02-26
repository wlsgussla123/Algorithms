package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int K;
	private int N,M;
	private int[][] map;
	private boolean[][][] visited;
	private int[][] mdirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	private int[][] hdirs =  {{-1,2}, {-2,1}, {-2,-1}, {-1,-2}, {1,2}, {2,1}, {1,-2}, {2,-1}};
	private final int BLANK = 0, WALL = 1, MAX = 987654321;
	private int answer = MAX;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1][K+1];
	}
	
	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		K = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private void solution() {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {1,1,K,0}; // 좌표, K, cnt
		visited[1][1][K] = true;
		q.add(pos);
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int k = cur[2];
			int cnt = cur[3];

			if(x == N && y == M) {
				answer = answer > cnt ? cnt : answer;
				continue;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + mdirs[i][0];
				int ny = y + mdirs[i][1];
				
				if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny][k]) {
					if(map[nx][ny] == WALL) continue;
					visited[nx][ny][k] = true;
					int[] next = {nx,ny,k,cnt+1};
					q.add(next);
				}
			}
			
			if(k >= 1) {
				for(int i=0; i<8; i++) {
					int nx = x + hdirs[i][0];
					int ny = y + hdirs[i][1];
					
					if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny][k-1]) {
						if(map[nx][ny] == WALL) continue;
						visited[nx][ny][k-1] = true;
						int[] next = {nx, ny, k-1, cnt+1};
						q.add(next);
					}
				}				
			}
		}
	}
	
	public void run() throws IOException {
		input();
		solution();
		if(answer == MAX) System.out.println("-1");
		else System.out.println(answer);
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

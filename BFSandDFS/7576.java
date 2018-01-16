package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private boolean[][] visited;
	private int[][] map;
	private int[][] answerMap;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	private int answer = -1;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][M+1];
		answerMap = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				answerMap[i][j] = map[i][j];
			}
		}
	}
	
	// 토마토가 모두 익지 못 하였는가?
	private void check() {
		boolean flag = false;
		for(int i=1; i<=N; i++) {
			if(flag) break;
			for(int j=1; j<=M; j++) {
				if(answerMap[i][j] == 0) {
					answer = -1;
					flag = true;
					break;
				}
			}
		}
	}
	
	private void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();

		// 일단, 익은 토마토 저장.
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					int[] pos = {i,j,0};
					visited[i][j] = true;
					q.add(pos);
				}
			}
		}
		
		// 익은 토마토 하나씩 꺼내서 옆에 고고
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int c = cur[2];
			
			answerMap[x][y] = 1;
			
			if(answer < c) answer = c;
			for(int i=0; i<4; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				
				if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny] && map[nx][ny] == 0) {
					visited[nx][ny] = true;
					int[] next = {nx,ny,c+1};
					q.add(next);
				}
			}
		}
		
		check();
	}
	
	public void run() throws IOException {
		input();
		bfs();
		System.out.println(answer);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

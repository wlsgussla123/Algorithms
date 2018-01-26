package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[][] map;
	private boolean[][] visited;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	private int answer = 987654321;
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private void clear() {
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				visited[i][j] = false;
	}
	
	private void makeArea(int x, int y, int cnt) {
		for(int i=0; i<4; i++) {
			int nx = x + dirs[i][0];
			int ny = y + dirs[i][1];
			
			if(nx>=1 && nx<=N && ny>=1 && ny<=N && !visited[nx][ny] && map[nx][ny] != 0) {
				visited[nx][ny] = true;
				map[nx][ny] = cnt;
				makeArea(nx, ny, cnt);
			}
		}
	}
	
	// 0이 아닌데 origin과 다르면 다른 대륙.
	private void bfs(int x, int y, int cnt, int origin) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {x,y,cnt};
		q.add(pos);
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			int cc = cur[2];
			
			for(int i=0; i<4; i++) {
				int nx = cx + dirs[i][0];
				int ny = cy + dirs[i][1];
				
				if(nx>=1 && nx<=N && ny>=1 && ny<=N && !visited[nx][ny]) {
					visited[nx][ny] = true;
					if(map[nx][ny] == 0) {
						int[] next = {nx,ny,cc+1};
						q.add(next);
					} else if(map[nx][ny] == origin) {
						int[] next = {nx,ny,cc};
						q.add(next);
					} else if(map[nx][ny] !=0 && map[nx][ny] != origin) {
						if(answer > cc) answer = cc;
					}
				}
			}
		}
	}
	
	public void run() throws IOException {
		input();
		int cnt = 1;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j] != 0 && !visited[i][j]) {
					visited[i][j] = true;
					map[i][j] = cnt;
					makeArea(i, j, cnt++);
				}
			}
		}
		clear();
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j] != 0 && !visited[i][j]) {
					visited[i][j] = true;
					bfs(i,j,0, map[i][j]);
					clear();
				}
			}
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

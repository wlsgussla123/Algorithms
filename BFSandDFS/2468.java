package algo;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N;
	private boolean[][] visited;
	private int[][] map;
	private int[][] originMap;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	private int answer = 0;
	private int MAX = 0;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
		originMap = new int[N+1][N+1];
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
				originMap[i][j] = map[i][j];
				if(map[i][j] > MAX) {
					MAX = map[i][j];
				}
			}
		}
	}
	
	private void recover() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				map[i][j] = originMap[i][j];
			}
		}
	}
	
	private void clear() {
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				visited[i][j] = false;
	}
	
	private void bfs(int row, int col, int height) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {row, col};
		q.add(pos);
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			visited[x][y] = true;
			
			for(int i=0; i<4; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				
				if(nx>=1 && nx<=N && ny>=1 && ny<=N) {
					if(map[nx][ny] <= height) {
						map[nx][ny] = 0;
					}
					
					if(!visited[nx][ny] && map[nx][ny] > height) {
						visited[nx][ny] = true;
						int[] next = {nx, ny};
						q.add(next);
					}
				}
			}
		}
	}
	
	private void checkArea(int row, int col, int cnt) {
		if(answer < cnt) answer = cnt;
		
		for(int i=0; i<4; i++) {
			int nx = row + dirs[i][0];
			int ny = col + dirs[i][1];
			
			if(nx>=1 && nx<=N && ny>=1 && ny<=N && !visited[nx][ny] && map[nx][ny] != 0) {
				visited[nx][ny] = true;
				checkArea(nx, ny, cnt);
			}
		}
	}
	
	public void run() throws IOException {
		input();
		
		// h는 강수량, i,j는 좌표
		for(int h=0; h<=MAX; h++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(!visited[i][j]) {
						bfs(i,j,h);
					}
				}
			}
			clear(); // 방문배열 초기화
			
			int cnt = 1;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(!visited[i][j] && map[i][j] != 0) {
						visited[i][j] = true;
						checkArea(i,j,cnt++);
					}
				}
			}
			clear();
			recover();
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

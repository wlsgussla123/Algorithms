package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[][] map;
	private boolean[][] visited;
	private final int[] dx = {0,0,1,-1};
	private final int[] dy = {1,-1,0,0};
	private int answer = 0;
	private final int BLANK = 0, WALL = 1, VIRUS = 2;
	private StringTokenizer st = null;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	// 바이러스 퍼뜨리기
	private void expandVirus(int[][] map) {
		visited = new boolean[N+1][M+1];
		
		Queue<int[]> q = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(map[i][j] == VIRUS) {
					visited[i][j] = true;
					int[] pos = {i,j};
					q.add(pos);
				}
			}
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			map[x][y] = 2;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx<1 || nx>N || ny<1 || ny>M || visited[nx][ny]) continue;
				if(map[nx][ny] == WALL) continue;
				visited[nx][ny] = true;
				int[] next = {nx,ny};
				q.add(next);
			}
		}
	}
	
	private int getSafeZone(int[][] map) {
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(map[i][j] == BLANK) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	// 벽 설치
	private void makeWall(int x, int y, int cnt, int[][] map) {
		int[][] temp = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		temp[x][y] = 1;
		if(cnt == 3) {
			expandVirus(temp);
			int safe = getSafeZone(temp);
			answer = answer > safe ? answer : safe;
			return;
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(temp[i][j] == BLANK) {
					makeWall(i, j, cnt+1, temp);
				}
			}
		}
	}
	
	private void solution() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(map[i][j] == BLANK)
					makeWall(i,j,1,map);
			}
		}
		
		System.out.println(answer);
	}
	
	public void run() throws IOException {
		input();
		solution();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

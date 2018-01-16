package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,M,H;
	private boolean[][][] visited;
	private int[][][] map;
	private int[][][] answerMap;
	// 아래, 위, 동서남북
	private int[][] dirs = {{1,0,0}, {-1,0,0}, {0,0,1}, {0,0,-1}, {0,1,0}, {0,-1,0}};
	private int answer = -1;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[H+1][N+1][M+1];
		answerMap = new int[H+1][N+1][M+1];
		visited = new boolean[H+1][N+1][M+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		init();
		
		for(int k=1; k<=H; k++) {
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				for(int j=1; j<=M; j++) {
					map[k][i][j] = Integer.parseInt(st.nextToken());
					answerMap[k][i][j] = map[k][i][j];
				}
			}
		}
	}
	
	private void check() {
		boolean flag = false;
		for(int k=1; k<=H; k++) {
			if(flag) break;
			for(int i=1; i<=N; i++) {
				if(flag) break;
				for(int j=1; j<=M; j++) {
					if(answerMap[k][i][j] == 0) {
						answer = -1;
						flag = true;
						break;
					}
				}
			}
		}
	}
	
	private void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		
		for(int k=1; k<=H; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(!visited[k][i][j] && map[k][i][j] == 1) {
						int[] pos = {k,i,j,0}; // 익은 토마토의 좌표와 처음 값 0
						q.add(pos);
					}
				}
			}
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int z = cur[0];
			int x = cur[1];
			int y = cur[2];
			int c = cur[3];
			answerMap[z][x][y] = 1;

			if(answer < c) answer = c;
			
			for(int i=0; i<6; i++) {
				int nz = z + dirs[i][0];
				int nx = x + dirs[i][1];
				int ny = y + dirs[i][2];
				
				if(nz>=1 && nz<=H && nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nz][nx][ny] && map[nz][nx][ny] == 0) {
					int[] next = {nz,nx,ny,c+1};
					visited[nz][nx][ny] = true;
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

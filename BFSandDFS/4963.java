package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[][] map;
	private boolean[][] visited;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}, {-1,-1}, {-1,1}, {1,-1}, {1,1}};
	private boolean isContinue = true;
	
	// input
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
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		init();
		
		if(N == 0 && M == 0) {
			isContinue = false;
			return;
		}
		
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private void makeArea(int x, int y, int cnt) {
		for(int i=0; i<8; i++) {
			int nx = x + dirs[i][0];
			int ny = y + dirs[i][1];
			
			if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny] && map[nx][ny] == 1) {
				visited[nx][ny] = true;
				makeArea(nx, ny, cnt);
			}
		}
	}

	public void run() throws IOException {
		while(isContinue) {
			input();
			int cnt = 1;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(map[i][j] == 1 && !visited[i][j]) {
						visited[i][j] = true;
						makeArea(i, j, cnt++);
					}
				}
			}
			
			if(isContinue) System.out.println(cnt-1);			
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

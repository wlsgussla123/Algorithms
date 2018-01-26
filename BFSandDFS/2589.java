package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private char[][] map;
	private boolean[][] visited;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	private int answer = 0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
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
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=M; j++) {
				map[i][j] = input[j-1];
			}
		}
	}
	
	private void bfs(int x, int y, int cnt) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {x,y,cnt};
		q.add(pos);
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			int cc = cur[2];
			if(cc > answer) answer = cc;
			
			for(int i=0; i<4; i++) {
				int nx = cx + dirs[i][0];
				int ny = cy + dirs[i][1];
				
				if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny] && map[nx][ny] == 'L') {
					visited[nx][ny] = true;
					int[] next = {nx,ny,cc+1};
					q.add(next);
				}
			}
		}
	}
	
	private void clear() {
	 	for(int i=1; i<=N; i++)
			for(int j=1; j<=M; j++)
				visited[i][j] = false;
	}
	
	public void run() throws IOException {
		input();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(map[i][j] == 'L' && !visited[i][j]) {
					visited[i][j] = true;
					bfs(i,j,0);
					clear(); // for문 바깥쪽에서 하면 특정 좌표가 visited에 걸려서 안 들어가질 수 있다.
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

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,M,K;
	private int[][] map;
	private boolean[][] visited;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N][M];
		visited = new boolean[N][M];
	}
	
	// 입력의 depth는 매우 잘 나오는 편
	private void input() throws IOException {
		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=K; i++) {
			st = getStringTokenizer();
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			map[n][m] = 1;
		}
	}
	
	private void dfs(int x, int y, int cnt) {
		for(int i=0; i<4; i++) {
			int nx = x + dirs[i][0];
			int ny = y + dirs[i][1];
			
			if(nx>=0 && nx<N && ny>=0 && ny<M && !visited[nx][ny] && map[nx][ny] == 1) {
				visited[nx][ny] = true;
				dfs(nx,ny,cnt);
			}
		}
	}
	
	private void process() throws IOException {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					visited[i][j] = true;
					dfs(i,j,cnt++);
				}
			}
		}
		System.out.println(cnt);
	}

	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		while(T-->0) {
			input();
			process();
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

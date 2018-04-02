package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N=5, K;
	private int answer = 0;
	private int[][] map;
	private boolean[][] visited;
	private final int[] dx = {0,0,1,-1};
	private final int[] dy = {1,-1,0,0};
	private final int WALL = 1;
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		K = Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<K; i++) {
			st = getStringTokenizer();
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;
		}
	}
	
	private boolean checkArea(int x, int y) {
		return x>=1 && x<=N && y>=1 && y<=N;
	}
	
	private void dfs(int x, int y, int cnt) {
		if(x==N && y==N) {
			if(cnt == 25-K)
				answer++;
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!checkArea(nx, ny) || visited[nx][ny]) continue;
			if(map[nx][ny] == WALL) continue;
			visited[nx][ny] = true;
			dfs(nx,ny,cnt+1);
			visited[nx][ny] = false;
		}
	}
	
	public void run() throws IOException {
		input();
		visited[1][1] = true;
		dfs(1,1,1);
		bw.write(String.valueOf(answer));
		close();
	}
	
	private void close() throws IOException {
		bw.close();
		br.close();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	new Task().run();
    }
}

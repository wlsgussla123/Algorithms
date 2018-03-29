package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[][] map;
	private int[][] dp;
	private int answer = 0;
	private final int[] dx = {0,0,1,-1};
	private final int[] dy = {1,-1,0,0};
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
		dp = new int[N+1][N+1];
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
	
	private boolean checkArea(int x, int y) {
		return (x>=1 && x<=N && y>=1 && y<=N);
	}
	
	private int dfs(int x, int y) {
		if(dp[x][y] != 0) return dp[x][y];
		dp[x][y] = 1;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!checkArea(nx, ny) || map[nx][ny] <= map[x][y]) continue;
			dp[x][y] = Math.max(dp[x][y], dfs(nx,ny) + 1);
		}
		
		return dp[x][y];
	}
	
	public void run() throws IOException {
		input();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				answer = Math.max(answer, dfs(i,j));
			}
		}
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
    	Task task = new Task();
    	task.run();
    }
}

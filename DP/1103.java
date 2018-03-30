package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[][] map;
	private int[][] dp;
	private boolean[][] visited;
	private final int[] dx = {0,0,1,-1};
	private final int[] dy = {1,-1,0,0};
	private final int H = 0;
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][M+1];
		dp = new int[N+1][M+1];
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
				if(input[j-1] == 'H') {
					map[i][j] = H;
				} else {
					map[i][j] = input[j-1] - '0';
				}
			}
		}
	}
	
	private boolean checkArea(int x, int y) {
		return x>=1 && x<=N && y>=1 && y<=M;
	}
	
	private int dfs(int x, int y) throws IOException {
		if(!checkArea(x, y)) return 0;
		if(map[x][y] == H) return 0;
		if(visited[x][y]) {
			bw.write("-1");
			close();
			System.exit(0);
		}
		if(dp[x][y] != 0) return dp[x][y];
		
		visited[x][y] = true;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i] * map[x][y];
			int ny = y + dy[i] * map[x][y];
			
			dp[x][y] = Math.max(dp[x][y], dfs(nx,ny)+1);
		}
		visited[x][y] = false;
		
		return dp[x][y];
	}
	
	public void run() throws IOException {
		input();
		bw.write(String.valueOf(dfs(1,1)));
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

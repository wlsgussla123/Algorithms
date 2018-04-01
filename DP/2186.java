package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,M,K;
	private int answer = 0;
	private char[] word;
	private char[][] map;
	private int[][][] dp;
	private final int[] dx = {0,0,1,-1};
	private final int[] dy = {1,-1,0,0};
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[N+1][M+1];
		dp = new int[N+1][M+1][85];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=M; j++) {
				map[i][j] = input[j-1];
			}
		}
		st = getStringTokenizer();
		word = st.nextToken().toCharArray();
	}
	
	private boolean checkArea(int x, int y) {
		return x>=1 && x<=N && y>=1 && y<=M;
	}
	
	private int dfs(int x, int y, int idx) {
		if(idx == word.length-1) return 1;
		if(dp[x][y][idx] != 0) return dp[x][y][idx];
		
		for(int i=0; i<4; i++) {
			for(int k=1; k<=K; k++) {
				int nx = x + dx[i] * k;
				int ny = y + dy[i] * k;
				if(!checkArea(nx, ny)) continue;
				if(map[nx][ny] != word[idx+1]) continue;
				dp[x][y][idx] += dfs(nx, ny, idx+1);
			}
		}
		
		return dp[x][y][idx];
	}
	
	public void run() throws IOException {
		input();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(word[0] == map[i][j]) {
					answer += dfs(i,j,0);
				}
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
    	new Task().run();
    }
}

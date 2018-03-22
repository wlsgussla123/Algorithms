package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[][] dp;
	private int[][] map;
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[3][N+1];
		dp = new int[3][N+4];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=1; i<=2; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private void solution() {
		dp[1][1] = map[1][1];
		dp[2][1] = map[2][1];
		
		for(int i=2; i<=N; i++) {
			dp[1][i] = Math.max(dp[2][i-1], dp[2][i-2]) + map[1][i];
			dp[2][i] = Math.max(dp[1][i-1], dp[1][i-2]) + map[2][i];
		}
		
		System.out.println(Math.max(dp[1][N], dp[2][N]));
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		for(int i=1; i<=T; i++) {
			input();
			solution();
		}
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

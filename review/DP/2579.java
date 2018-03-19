package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] floor;
	private int[][] dp;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		floor = new int[N+1];
		dp = new int[N+1][2];
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			floor[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private int solution() {
		dp[1][0] = floor[1];
		dp[1][1] = floor[1];
		if(N<=1) return dp[1][0];
		
		for(int i=2; i<=N; i++) {
			dp[i][0] = floor[i] + dp[i-1][1];
			dp[i][1] = floor[i] + Math.max(dp[i-2][0], dp[i-2][1]);
		}
		
		return Math.max(dp[N][0], dp[N][1]);
	}
	
	public void run() throws IOException {
		input();
		System.out.println(solution());
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

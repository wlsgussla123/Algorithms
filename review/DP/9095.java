package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] dp;
	private int answer = 0;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		dp = new int[N+4];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
	}

	private int solution() {
		if(N<=3) return dp[N];
		for(int i=4; i<=N; i++) {
			dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
		}
		
		return dp[N];
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		for(int i=1; i<=T; i++) {
			input();
			System.out.println(solution());
		}
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

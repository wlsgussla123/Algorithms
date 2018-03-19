package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int X;
	private int[] dp;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		X = Integer.parseInt(st.nextToken());
		if(X<=3) dp = new int[4];
		else dp = new int[X+1];
	}
	
	private int solution() {
		dp[0] = 0;
		dp[1] = 0;
		dp[2] = dp[3] = 1;
		if(X<=3) {
			return dp[X];
		}
		
		for(int i=4; i<=X; i++) {
			dp[i] = dp[i-1] + 1;
			if(i%2 == 0 && dp[i/2] + 1 < dp[i]) {
				dp[i] = dp[i/2] + 1;
			}
			if(i%3 == 0 && dp[i/3] + 1 < dp[i]) {
				dp[i] = dp[i/3] + 1;
			}
		}
		
		return dp[X];
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

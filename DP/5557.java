package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] number;
	private long[][] dp;
	private int answer = 0;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		dp = new long[N+1][21];
		number = new int[N];
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private boolean isPromising(int num) {
		return (num>=0 && num<=20);
	}
	
	private void solution() {
		dp[0][number[0]] = 1;
		
		for(int i=1; i<N-1; i++) {
			for(int j=0; j<=20; j++) {
				if(dp[i-1][j] > 0) {
					if(isPromising(j + number[i])) {
						dp[i][j + number[i]] += dp[i-1][j];
					}
					
					if(isPromising(j - number[i])) {
						dp[i][j - number[i]] += dp[i-1][j];
					}
				}
			}
		}

		System.out.println(dp[N-2][number[N-1]]);
	}
	
	public void run() throws IOException {
		input();
		solution();
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

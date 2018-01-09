package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] dp;
	private int[] sequence;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		dp = new int[N+1];
		sequence = new int[N+1];
	}

	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
	
		st= getStringTokenizer();
		for(int i=1; i<=N; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private void sequenceSum() {
		dp[1] = sequence[1];
		for(int i=1; i<=N; i++) {
			if(dp[i-1] + sequence[i] > sequence[i]) {
				dp[i] = dp[i-1] + sequence[i];
			} else {
				dp[i] = sequence[i];
			}
		}
		
		int answer = dp[1];
		for(int i=2; i<=N; i++) {
			if(dp[i] > answer) answer = dp[i];
		}
		
		System.out.println(answer);
	}

	public void run() throws IOException {
		input();
		sequenceSum();
		br.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] floor;
	private long[][] dp;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		dp = new long[N+3][3];
		floor = new int[N+3];
	}

	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			floor[i] = Integer.parseInt(st.nextToken());
		}
	}

	private long max(long a, long b) {
		return a > b ? a : b;
	}

	private void process() {
		dp[1][1] = dp[1][2] = floor[1];
		
		for(int i=2; i<=N; i++) {
			dp[i][1] = dp[i-1][2] + floor[i]; // 바로 전에서 왔다고 하면 바로 전 칸에서는 무조건 두번째 전에서만 왔겠지
			dp[i][2] = max(dp[i-2][1], dp[i-2][2]) + floor[i]; // 두 칸 전에 왔다고 하면, 두 칸 전에는 한 칸과 두 칸 전에 언제 왔을까?
		}
		
		long answer = max(dp[N][1], dp[N][2]);
		System.out.println(answer);
	}

	public void run() throws IOException {
		input();
		process();
		br.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

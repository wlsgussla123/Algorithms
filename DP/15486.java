package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] t;
	private int[] p;
	private int[] dp;
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		t = new int[N+1];
		p = new int[N+1];
		dp = new int[N+51];
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private void solution() throws IOException {
		for(int i=1; i<=N; i++) {
			if(dp[i] > dp[i+1]) {
				dp[i+1] = dp[i]; // 내일까지 번 돈이 오늘보다 적으면 오늘 금액을 내일에 넣는다.
			}
			
			// 상담하면 (오늘 + t)일에 p만큼 정산 => 이번 상담을 진행하는 것이 이익인지 판단
			if(dp[i+t[i]] < dp[i] + p[i]) {
				dp[i+t[i]] = dp[i] + p[i];
			}
		}
		bw.write(String.valueOf(dp[N+1]));
	}
	
	public void run() throws IOException {
		input();
		solution();
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

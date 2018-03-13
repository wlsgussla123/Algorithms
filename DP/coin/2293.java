package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,K;
	private int[] coin;
	private int[] dp;
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coin = new int[N+1];
		dp = new int[K+1];
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			coin[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private void solution() throws IOException {
		dp[0] = 1; // k=1일 경우 1원으로 채움, k=3일 경우 3원으로 채우는 경우 등..
		for(int i=0; i<N; i++) {
			for(int j=coin[i]; j<=K; j++) {
				dp[j] = dp[j] + dp[j-coin[i]];
			}
		}
		System.out.println(dp[K]);
	}
	
	public void run() throws IOException {
		input();
		solution();
		close();
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

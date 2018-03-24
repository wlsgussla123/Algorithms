package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[][] color;
	private int[][] dp;
	private final Scanner sc = new Scanner(System.in);
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		color = new int[N+1][3];
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=0; j<3; j++) {
				color[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[N+4][3];
	}
	
	private void solution() throws IOException {
		dp[1][0] = color[1][0]; // R
		dp[1][1] = color[1][1]; // G
		dp[1][2] = color[1][2]; // B
		for(int i=2; i<=N; i++) {
			dp[i][0] = color[i][0] + Math.min(dp[i-1][1], dp[i-1][2]); // R
			dp[i][1] = color[i][1] + Math.min(dp[i-1][0], dp[i-1][2]); // G
			dp[i][2] = color[i][2] + Math.min(dp[i-1][0], dp[i-1][1]); // B
		}
		System.out.println(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]));
	}
	
	public void run() throws IOException {
		input();
		solution();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

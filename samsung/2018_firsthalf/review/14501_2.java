package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Schedule {
	int time;
	int cost;
	public Schedule(int time, int cost) {
		this.time = time;
		this.cost = cost;
	}
}

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N;
		private int ans = 0;
		private int[] dp;
		private ArrayList<Schedule> list = new ArrayList();
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			dp = new int[N+5];
			for(int i=0; i<=N; i++)
				dp[i] = -1;
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				int time = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				list.add(new Schedule(time, cost));
			}
		}
		
		private int solve(int idx) {
			if(idx >= N) return 0;
			if(dp[idx] != -1) return dp[idx];
			
			int ret = solve(idx+1);
			int ret2 = 0;
			if(idx + list.get(idx).time <= N) {
				ret2 = solve(idx + list.get(idx).time) + list.get(idx).cost;
			}
			
			return dp[idx] = Math.max(ret, ret2);
		}
		
		public void run() throws IOException {
			input();
			bw.write(String.valueOf(solve(0)));
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

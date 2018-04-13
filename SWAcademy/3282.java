package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,C;
		private int ans;
		private int[][] knapsack;
		private int[] w;
		private int[] v;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			ans = 0;
			knapsack = new int[105][1005];
			for(int i=0; i<=100; i++) {
				for(int j=0; j<=1000; j++) {
					knapsack[i][j] = -1;
				}
			}
			w = new int[N+1];
			v = new int[N+1];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				int weight = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				w[i] = weight;
				v[i] = value;
			}
		}
		
		private int solution(int idx, int W) {
			if(knapsack[idx][W] != -1) {
				return knapsack[idx][W];
			}
			
			if(idx==0 || W==0) {
				return knapsack[idx][W] = 0;
			} else {
				int ret1 = -1;
				int ret2 = -1;
				if(W >= w[idx]) {
					ret1 = solution(idx-1, W - w[idx]) + v[idx];
				}
				ret2 = solution(idx-1, W);
				knapsack[idx][W] = Math.max(ret1, ret2);
			}
			
			return knapsack[idx][W];
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				bw.write("#" + i + " " + String.valueOf(solution(N,C))+"\n");
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

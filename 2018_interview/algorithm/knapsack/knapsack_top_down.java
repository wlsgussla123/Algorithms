package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,W;
		private int[] v;
		private int[] w;
		private int[][] k; 
		private int ans;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			ans = 0;
			v = new int[N+1];
			w = new int[N+1];
			k = new int[105][1005];
			for(int i=0; i<=100; i++) {
				for(int j=0; j<=1000; j++) {
					k[i][j] = -1;
				}
			}
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				w[i] = Integer.parseInt(st.nextToken());
				v[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		private int knapsack(int idx, int weight) {
			if(idx == 0 || weight == 0) {
				return 0;
			}
			
			if(k[idx][weight] != -1) {
				return k[idx][weight];
			}
			k[idx][weight] = 0;
			
			int ret1 = knapsack(idx-1, weight);
			int ret2 = -1;
			if(weight >= w[idx]) {
				ret2 = knapsack(idx-1, weight - w[idx]) + v[idx];
			}
			
			k[idx][weight] = Math.max(ret1, ret2);
			return k[idx][weight];
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				bw.write("#" + i + " " + String.valueOf(knapsack(N, W))+"\n");
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

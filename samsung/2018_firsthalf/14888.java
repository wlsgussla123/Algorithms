package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N;
		private final int MIN = -1000000000, MAX = 1000000000;
		private int min = MAX, max = MIN;
		private int[] arr;
		private int[] op;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			arr = new int[N];
			op = new int[4];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			init();
			
			st = getStringTokenizer();
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			st = getStringTokenizer();
			for(int i=0; i<4; i++) {
				op[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		private void dfs(int idx, int sum) {
			if(idx==N) {
				min = min > sum ? sum : min;
				max = max > sum ? max : sum;
				return;
			}
			
			for(int i=0; i<4; i++) {
				if(op[i] <= 0) continue;
				op[i]--;
				if(i==0) {
					dfs(idx+1, sum+arr[idx]);
				} else if(i==1) {
					dfs(idx+1, sum-arr[idx]);
				} else if(i==2) {
					dfs(idx+1, sum*arr[idx]);
				} else {
					dfs(idx+1, sum/arr[idx]);
				}
				op[i]++;
			}
		}
		
		public void run() throws IOException {
			input();
			for(int i=0; i<4; i++) {
				if(op[i] <= 0) continue;
				op[i]--;
				if(i==0) {
					dfs(2, arr[0]+arr[1]);
				} else if(i==1) {
					dfs(2, arr[0]-arr[1]);
				} else if(i==2) {
					dfs(2, arr[0]*arr[1]);
				} else {
					dfs(2, arr[0]/arr[1]);
				}
				op[i]++;
			}
			
			bw.write(String.valueOf(max)+"\n");
			bw.write(String.valueOf(min)+"\n");
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

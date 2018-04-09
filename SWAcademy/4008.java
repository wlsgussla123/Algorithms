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
		private int[] op;
		private int[] arr;
		private int min;
		private int max;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			op = new int[4];
			arr = new int[N];
			min = 1000000000;
			max = -1000000000;
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			init();
			
			st = getStringTokenizer();
			for(int i=0; i<4; i++) {
				op[i] = Integer.parseInt(st.nextToken());
			}
			
			st = getStringTokenizer();
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		private void dfs(int sum, int idx) throws IOException {
			if(idx == N) {
				max = max > sum ? max : sum;
				min = min > sum ? sum : min;
				return;
			}
			
			for(int i=0; i<4; i++) {
				if(op[i] > 0) {
					op[i]--;
					if(i==0) {
						dfs(sum+arr[idx], idx+1);
					} else if(i==1) {
						dfs(sum-arr[idx], idx+1);
					} else if(i==2) {
						dfs(sum*arr[idx], idx+1);
					} else {
						dfs(sum/arr[idx], idx+1);
					}
					op[i]++;					
				}
			}
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int idx=1; idx<=T; idx++) {
				input();
				for(int i=0; i<4; i++) {
					if(op[i] > 0) {
						op[i]--;
						if(i==0) {
							dfs(arr[0]+arr[1], 2);
						} else if(i==1) {
							dfs(arr[0]-arr[1], 2);
						} else if(i==2) {
							dfs(arr[0]*arr[1], 2);
						} else {
							dfs(arr[0]/arr[1], 2);
						}
						op[i]++;
					}
				}
				bw.write(String.valueOf("#"+ idx + " " + (max-min))+"\n");
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

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
		private int ans;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			ans = 0;
			v = new int[N];
			w = new int[N];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				w[i] = Integer.parseInt(st.nextToken());
				v[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		private void knapsack(int value, int weight, int idx) {
			if(weight > W) {
				return;
			}
			ans = ans > value ? ans : value;
			
			for(int i=idx+1; i<N; i++) {
				knapsack(value + v[i], weight + w[i], i);
			}
		}
		
		private void solve() throws IOException {
			for(int i=0; i<N; i++) {
				knapsack(v[i], w[i], i);
			}
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				solve();
				bw.write("#" + i + " " + String.valueOf(ans)+"\n");
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

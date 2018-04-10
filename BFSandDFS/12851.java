package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,K;
		private final int MAX = 987654321;
		private boolean[] visited;
		private int[] memo;
		private int ans_time = MAX;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			visited = new boolean[100001];
			memo = new int[100001];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			init();
		}
		
		private boolean checkArea(int x) {
			return x>=0 && x<=100000; 
		}
		
		private void bfs() {
			Queue<int[]> q = new LinkedList();
			int[] init = {N, 0};
			q.add(init);
			
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				int x = cur[0];
				int time = cur[1];
				
				if(x == K) {
					if(ans_time > time) {
						ans_time = time;
						memo[time]++;
					} else if(ans_time == time) {
						memo[time]++;
					}
					continue;
				}
				visited[x] = true;
				
				if(checkArea(x-1) && !visited[x-1]) {
					int[] next = {x-1, time+1};
					q.add(next);
				}
				if(checkArea(x+1) && !visited[x+1]) {
					int[] next = {x+1, time+1};
					q.add(next);
				}
				if(checkArea(x*2) && !visited[x*2]) {
					int[] next = {x*2, time+1};
					q.add(next);
				}
			}
		}
		
		public void run() throws IOException {
			input();
			bfs();
			bw.write(String.valueOf(ans_time)+"\n");
			bw.write(String.valueOf(memo[ans_time])+"\n");
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

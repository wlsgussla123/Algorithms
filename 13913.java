package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,K;
		private int[] pos;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			pos = new int[100001];
			for(int i=0; i<=100000; i++) {
				pos[i] = -1;
			}
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
		
		private void bfs() throws IOException {
			Queue<int[]> q = new LinkedList();
			int[] init = {N,0};
			pos[N] = N;
			q.add(init);
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				int x = cur[0];
				int time = cur[1];
				
				if(x==K) {
					System.out.println(time);
					break;
				}
				
				if(checkArea(x+1) && pos[x+1] == -1) {
					pos[x+1] = x;
					int[] next = {x+1, time+1};
					q.add(next);
				}
				
				if(checkArea(x-1) && pos[x-1] == -1) {
					pos[x-1] = x;
					int[] next = {x-1, time+1};
					q.add(next);
				}
				
				if(checkArea(x*2) && pos[x*2] == -1) {
					pos[x*2] = x;
					int[] next = {x*2, time+1};
					q.add(next);
				}
			}
			
			
			
			if(N==K) {
				System.out.println(N);
			} else {
				Stack<Integer> s = new Stack();
				s.push(K);
				int idx = K;
				while(true) {
					if(pos[idx] == N) {
						s.push(N);
						break;
					}
					s.push(pos[idx]);
					idx = pos[idx];
				}
				
				while(!s.isEmpty()) {
					System.out.print(s.pop() + " ");
				}				
			}
		}
		
		public void run() throws IOException {
			input();
			bfs();
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[] L;
	private static int[] J;
	private static boolean[] visited;
	private static int ans = 0;

	public static void main(String[] args) throws IOException {
		InputClass.input();
		new Solution().run();
		InputClass.close();
	}

	static class Solution {
		public void run() throws IOException {
			for(int i=0; i<N; i++) {
				visited[i] = true;
				giveAppreciation(i, 100 - L[i], J[i]);
				visited[i] = false;
			}
			
			InputClass.BW.write(String.valueOf(ans));
		}
		
		public void giveAppreciation(int idx, int hp, int happy) {
			if(hp <= 0) {
				return;
			}
			
			ans = ans > happy ? ans : happy;
			for(int i=idx+1; i<N; i++) {
				if(visited[i])
					continue;
				visited[i] = true;
				giveAppreciation(i, hp - L[i], happy + J[i]);
				visited[i] = false;
			}
		}
	}
	
	static class InputClass {
		public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
		public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
		private static StringTokenizer st = null;
		
		public static void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			L = new int[N];
			J = new int[N];
			visited = new boolean[N];
			
			st = getStringTokenizer();
			for(int i=0; i<N; i++) {
				L[i] = Integer.parseInt(st.nextToken());
			}
			
			st = getStringTokenizer();
			for(int i=0; i<N; i++) {
				J[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		public static void close() throws IOException {
			BR.close();
			BW.close();
		}
		
		public static StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(InputClass.BR.readLine(), " ");
		}
	}
}

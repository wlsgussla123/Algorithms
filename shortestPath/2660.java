package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N;
		private int[][] map;
		private final int INF = 987654321;
		private int[] res;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			res = new int[N+1];
			map = new int[N+1][N+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++ ) {
					map[i][j] = INF;
				}
			}
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			init();
			
			int a,b;
			while(true) {
				st = getStringTokenizer();
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				if(a == -1) break;
				map[a][b] = 1;
				map[b][a] = 1;
			}
		}
		
		private void solve() throws IOException {
			for(int k=1; k<=N; k++) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						if(map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
							map[j][i] = map[i][j];
						}
					}
				}
			}
			
			int max = 0;
			int min = INF;
			for(int i=1; i<=N; i++) {
				max = 0;
				for(int j=1; j<=N; j++) {
					if(i==j) continue;
					max = Math.max(max, map[i][j]);
				}
				res[i] = max;
				min = Math.min(min, max);
			}
			
			int cnt = 0;
			for(int i=1; i<=N; i++) {
				if(res[i] == min) cnt++; 
			}
			System.out.println(min + " " + cnt);
			for(int i=1; i<=N; i++) {
				if(min == res[i]) System.out.print(i + " ");
			}
		}
		
		public void run() throws IOException {
			input();
			solve();
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

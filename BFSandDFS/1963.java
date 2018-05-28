package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}
	
	static class Prime {
		private static boolean[] isPrime = new boolean[10000];
		public static boolean[] getPrime() {
			Arrays.fill(isPrime, true);
			isPrime[1] = false;
			for(int i=2; i<=9999; i++) {
				if(isPrime[i]) {
					for(int j=i+i; j<=9999; j+=i) {
						isPrime[j] = false;
					}
				}
			}
			
			return isPrime;
		}
	}

	static class Solution {
		private int S,E;
		private boolean[] visited;
		private boolean[] isPrime = Prime.getPrime();
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			visited = new boolean[10000];
			isPrime = Prime.getPrime();
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			S = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			init();
		}
		
		private int[] convertToArray(int num) {
			int[] numArr = new int[4];
			numArr[0] = (num/1000) % 10;
			numArr[1] = (num/100) % 10;
			numArr[2] = (num/10) % 10;
			numArr[3] = (num/1) % 10;
			return numArr;
		}
		
		private int getNextPrime(int[] numArr, int index, int num) {
			numArr[index] = num;
			return numArr[0] * 1000 + numArr[1] * 100 + numArr[2] * 10 + numArr[3]; 
		}
		
		private int solution(int S, int E) throws IOException {
			Queue<int[]> q = new LinkedList<int[]>();
			int[] init = {S,0};
			visited[S] = true;
			q.add(init);
			
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				int num = cur[0];
				int cnt = cur[1];
				
				if(num == E) {
					return cnt;
				}
				
				for(int i=0; i<4; i++) {
					int[] numArr = convertToArray(num);
					for(int j=0; j<=9; j++) {
						if(i==0 && j==0) continue;
						if(numArr[i] == j) continue;
						int nn = getNextPrime(numArr, i, j);
						if(!isPrime[nn] || visited[nn]) continue;
						
						int[] next = {nn, cnt+1};
						visited[nn] = true;
						q.add(next);
					}
				}
			}
			
			return -1;
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			
			for(int i=1; i<=T; i++) {
				input();
				int res = solution(S, E);
				if(res == -1) {
					bw.write("Impossible\n");
				} else {
					bw.write(String.valueOf(res)+"\n");
				}
			}
			
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

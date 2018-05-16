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
		private int N,M;
		private int[] prime;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			prime = new int[M+1];
			Arrays.fill(prime, 0);
		}
		
		private void eratos() {
			prime[1] = -1;
			for(int i=2; i<=M; i++) {
				for(int j=i+i; j<=M; j=j+i) {
					if(j%i == 0) {
						prime[j] = -1;
					}
				}
			}
		}
		
		private void solve() throws IOException {
			for(int i=N; i<=M; i++) {
				if(prime[i] == 0) {
					bw.write(String.valueOf(i)+"\n");
				}
			}
		}
		
		public void run() throws IOException {
			input();
			eratos();
			solve();
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

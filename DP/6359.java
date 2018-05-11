package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N;
		private boolean[] isLocked;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			isLocked = new boolean[N+1];
		}
		
		private void solve() throws IOException {
			int cnt = N;
			
			for(int i=2; i<=N; i++) {
				for(int j=i; j<=N; j+=i) {
					if(isLocked[j]) {
						cnt++;
						isLocked[j] = false;
					} else {
						cnt--;
						isLocked[j] = true;
					}
				}
			}
			
			bw.write(String.valueOf(cnt)+"\n");
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int i=1; i<=T; i++) {
				input();
				solve();
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

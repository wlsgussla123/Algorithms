package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private String T;
		private String P;
		private int N,M;
		private int[] fail;
		private ArrayList<Integer> res = new ArrayList();
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), "");
		}
		
		private void input() throws IOException {
			T = br.readLine();
			P = br.readLine();
			N = T.length();
			M = P.length();
		}
		
		private void makeFail() {
			fail = new int[M];
			int j=0;
			for(int i=1; i<M; i++) {
				while(j>0 && P.charAt(i) != P.charAt(j))
					j = fail[j-1];
				
				if(P.charAt(i) == P.charAt(j))
					fail[i] = ++j;
			}
		}
		
		private void kmp() {
			int j=0;
			for(int i=0; i<N; i++) {
				while(j>0 && T.charAt(i) != P.charAt(j)) 
					j = fail[j-1];
				
				if(T.charAt(i) == P.charAt(j)) {
					if(j == M-1) { // 패턴을 전부 탐색.
						res.add(i-M+2);
						j = fail[j];
					} else {
						j++;
					}
				}
			}
		}
		
		private void solve() throws IOException {
			makeFail();
			kmp();
			bw.write(String.valueOf(res.size()) + "\n");
			for(Integer num : res) {
				bw.write(String.valueOf(num) + "\n");
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

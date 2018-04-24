package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private long A,B;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
		}
		
		private long solve(long p, long q) {
			if(q==0) {
				return p;
			}
			
			return solve(q, p%q);
		}
		
		public void run() throws IOException {
			input();
			long gcd = 0;
			if(A>B) {
				gcd = solve(A,B);
			} else {
				gcd = solve(B,A);
			}
			System.out.println(gcd);
			System.out.println((A*B)/gcd);
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

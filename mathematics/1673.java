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
		private int n,k;
		private int sum;
		
		private final Scanner sc = new Scanner(System.in);
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			while(sc.hasNextInt()) {
				n = sc.nextInt();
				k = sc.nextInt();
				sum = n;
				solve();
			}
		}
		
		private void solve() throws IOException {
			int remain = 0;
			while(true) {
				sum += n/k;
				remain = n%k + n/k;
				n = remain;
				if(remain < k) break;
			}
			bw.write(String.valueOf(sum)+"\n");
		}
		
		public void run() throws IOException {
			input();
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

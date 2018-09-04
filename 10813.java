package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static int[] arr;

	public static void main(String[] args) throws IOException {
		InputClass.input();
		InputClass.close();
	}
	
	static class InputClass {
		public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
		public static final BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(System.out));
		private static StringTokenizer st = null;
		
		public static void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N+1];
			for(int i=1; i<=N; i++) {
				arr[i] = i;
			}
			
			for(int i=0; i<M; i++) {
				st = getStringTokenizer();
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int tmp = arr[a];
				arr[a] = arr[b];
				arr[b] = tmp;
			}
			
			for(int i=1; i<=N; i++) {
				BW.write(String.valueOf(arr[i]) + " ");
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

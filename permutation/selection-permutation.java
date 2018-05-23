package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}

	static class Solution {
		private int N;
		private int[] arr;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			arr = new int[N];
			
			st = getStringTokenizer();
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		private void swap(int[] arr, int a, int b) {
			int temp = arr[a];
			arr[a] = arr[b];
			arr[b] = temp;
		}
		
		private void permutation(int[] arr, int n, int r, int depth) {
			if(depth == r) {
				print(arr, r);
				return;
			}
			
			for(int i=depth; i<N; i++) {
				swap(arr, depth, i);
				permutation(arr, n, r, depth+1);
				swap(arr, depth, i);
			}
		}
		
		public void run() throws IOException {
			input();
			permutation(arr, N, 4, 0);
			close();
		}
		
		private void print(int[] arr, int r) {
			for(int i=0; i<r; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

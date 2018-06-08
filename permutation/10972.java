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
		private int[] arr;
		private int N;
		
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
		
		private void swap(int[] arr, int p, int q) {
			int temp = arr[p];
			arr[p] = arr[q];
			arr[q] = temp;
		}
		
		private boolean nextPermutation(int[] arr) {
			if(arr == null || arr.length < 2) {
				return false;
			}
			
			int i = arr.length - 1;
			while(i > 0 && arr[i - 1] >= arr[i]) {
				i--;
			}
			
			if(i <= 0) {
				return false;
			}
			
			int j = arr.length - 1;
			while(arr[j] <= arr[i - 1]) {
				j--;
			}
			
			swap(arr, i - 1 , j);
			reverse(arr, i, arr.length - 1);
			
			return true;
		}
		
		private void reverse(int[] arr, int left, int right) {
			while(left < right) {
				swap(arr, left, right);
				left++;
				right--;
			}
		}
		
		private void print(int[] arr) {
			for(Integer num : arr) {
				System.out.print(num + " ");
			}
			System.out.println();
		}

		public void run() throws IOException {
			input();
			if(nextPermutation(arr)) {
				print(arr);
			} else {
				System.out.println("-1");
			}

			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

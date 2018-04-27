package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private final int N = 4;
		private int[] arr = new int[N];
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
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
		
		private int quicksort(int[] arr, int left, int right) {
			int pivot = right;
			int low = left;
			int high = right - 1;
			
			while(low < high) {
				while(low < high && arr[low] <= arr[pivot]) {
					low++;
				}
				
				while(low < high && arr[pivot] <= arr[high]) {
					high--;
				}
				
				if(low < high) {
					swap(arr, low, high);
				}
			}
			if(arr[high] > arr[pivot]) {
				swap(arr, pivot, high);
				return high;
			}
			
			return pivot;
		}
		
		private void partition(int[] arr, int left, int right) {
			if(left < right) {
				int pivot = quicksort(arr, left, right);
				partition(arr, left, pivot-1);
				partition(arr, pivot+1, right);
			}
		}
		
		private void solve() throws IOException {
			partition(arr, 0, N-1);
			bw.write(String.valueOf(arr[0] * arr[2]));
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

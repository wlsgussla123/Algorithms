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
	
	static class QuickSort {
		public static void swap(int[] arr, int a, int b) {
			int temp = arr[a];
			arr[a] = arr[b];
			arr[b] = temp;
		}
		
		public static int getPivot(int[] arr, int left, int right) {
			int mid = (left + right) / 2;
			int pivot = -1;
			if(arr[left] > arr[right]) {
				pivot = arr[left] > arr[mid] ? mid : left; 
			} else {
				pivot = arr[right] > arr[mid] ? mid : right;
			}
			
			return pivot;
		}
		
		public static int partition(int[] arr, int left, int right) {
			int pivot = getPivot(arr, left, right);
			swap(arr, pivot, right);

			pivot = right;
			int low = left;
			int high = right-1;
			
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
			
			if(arr[pivot] < arr[high]) {
				swap(arr, pivot, high);
				return high;
			}
			
			return pivot;
		}
		
		public static void quicksort(int[] arr, int left, int right) {
			if(left < right) {
				int pivot = partition(arr, left, right);
				quicksort(arr, left, pivot-1);
				quicksort(arr, pivot+1, right);
			}
		}
	}
	
	static class Task {
		private int N,M;
		private int[] arr;
		private int[] res;
		private boolean[] visited;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			arr = new int[N+1];
			res = new int[M+1];
			visited = new boolean[N+1];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			
			st = getStringTokenizer();
			for(int i=1; i<=N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		private void permutation(int depth, int prev) throws IOException {
			if(depth > M) {
				for(int i=1; i<=M; i++) {
					bw.write(String.valueOf(res[i]) + " ");
				}
				bw.write("\n");
				return;
			}
			
			for(int i=1; i<=N; i++) {
				if(prev > arr[i] || visited[i]) continue;
				res[depth] = arr[i];
				visited[i] = true;
				permutation(depth+1, arr[i]);
				visited[i] = false;
			}
		}
		
		public void run() throws IOException {
			input();
			QuickSort.quicksort(arr, 1, N);
			permutation(1,0);
			close();
		}
		
		private void print() {
			for(int i=1; i<=N; i++) {
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

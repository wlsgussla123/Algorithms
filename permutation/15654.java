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
		
		private void merge(int[] arr, int left, int mid, int right) {
			int lIdx = left;
			int rIdx = mid+1;
			int sIdx = left;
			int[] sortArr = new int[N+1];
			
			while(lIdx <= mid && rIdx <= right) {
				while(lIdx <= mid && arr[lIdx] <= arr[rIdx]) {
					sortArr[sIdx++] = arr[lIdx++];
				}
				
				while(rIdx <= right && arr[rIdx] <= arr[lIdx]) {
					sortArr[sIdx++] = arr[rIdx++];
				}
			}
			
			if(lIdx <= mid) {
				for(int i=lIdx; i<=mid; i++) {
					sortArr[sIdx++] = arr[i];
				}
			} else {
				for(int i=rIdx; i<=right; i++) {
					sortArr[sIdx++] = arr[i];
				}
			}
			
			for(int i=left; i<=right; i++) {
				arr[i] = sortArr[i];
			}
		}
		
		private void mergesort(int[] arr, int left, int right) {
			if(left < right) {
				int mid = (left + right) / 2;
				mergesort(arr, left, mid);
				mergesort(arr, mid+1, right);
				merge(arr, left, mid, right);
			}
		}
		
		private void permutation(int depth) throws IOException {
			if(depth > M) {
				for(int i=1; i<=M; i++) {
					bw.write(String.valueOf(res[i]) + " ");
				}
				bw.write("\n");
				return;
			}
			
			for(int i=1; i<=N; i++) {
				if(visited[i]) continue;
				visited[i] = true;
				res[depth] = arr[i];
				permutation(depth+1);
				visited[i] = false;
			}
		}
		
		public void run() throws IOException {
			input();
			mergesort(arr, 1, N);
			permutation(1);
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

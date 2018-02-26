package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] arr;
	private int[] result;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		result = new int[N];
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private void merge(int[] arr, int start, int mid, int end) {
		int lIdx = start;
		int rIdx = mid+1;
		int idx = start; // 분할을 했을 때, 각각의 시작점이 다르므로 0이 아니다. (오른쪽 분할된 것은 시작이 0이 아님)
		int len = arr.length;
		int[] sortArr = new int[len];
		
		while(lIdx <= mid && rIdx <= end) {
			if(arr[lIdx] > arr[rIdx]) {
				sortArr[idx++] = arr[rIdx++];
			} else {
				sortArr[idx++] = arr[lIdx++];
			}
		}
		
		while(lIdx <= mid) {
			sortArr[idx++] = arr[lIdx++];
		}
		
		while(rIdx <= end) {
			sortArr[idx++] = arr[rIdx++];
		}
		
		// 마찬가지로, 0부터 변경된 부분만 바꿔준다.
		for(int i=start; i<idx; i++) {
			arr[i] = sortArr[i];
		}
	}
	
	private void mergeSort(int[] arr, int start, int end) {
		if(start < end) {
			int mid = (start + end) / 2;
			mergeSort(arr, start, mid);
			mergeSort(arr, mid+1, end);
			merge(arr, start, mid, end);
		}
	}
	
	private void print() {
		int len = arr.length;
		for(int i=0; i<len; i++)
			System.out.println(arr[i]);
	}
	
	public void run() throws IOException {
		input();
		mergeSort(arr, 0, arr.length-1);
		print();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

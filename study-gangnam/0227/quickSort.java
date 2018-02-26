package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] arr;
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
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	private int partition(int[] arr, int start, int end) {
		int pivot = end;
		int low = start;
		int high = end-1;
		
		while(low < high) {
			while(arr[low] <= arr[pivot] && low < high) {
				low++;
			}
			
			while(arr[high] >= arr[pivot] && low < high) {
				high--;
			}
			
			if(low < high) {
				swap(arr, low, high);
			}
		}
		if(arr[high] > arr[pivot]) {
			swap(arr, pivot, high);
			return high;
		} else {
			return pivot;
		}
	}
	
	private void quicksort(int[] arr, int start, int end) {
		if(start < end) {
			int pivot = partition(arr, start, end);
			quicksort(arr, start, pivot-1);
			quicksort(arr, pivot+1, end);
		}
	}
	
	private void print() {
		int len = arr.length;
		for(int i=0; i<len; i++)
			System.out.println(arr[i]);
	}
	
	public void run() throws IOException {
		input();
		quicksort(arr, 0, arr.length-1);
		print();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

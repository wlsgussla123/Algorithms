package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
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
		
		private int getPivot(int[] arr, int start, int end) {
			int l = start;
			int m = (start + end) / 2;
			int r = end;
			
			int temp = arr[l] > arr[r] ? l : r;
			temp = arr[temp] > arr[m] ? m : temp;
			return temp;
		}
		
		private int partition(int[] arr, int start, int end) {
			int pivot = getPivot(arr, start, end);
			swap(arr, pivot, end);
			pivot = end;
			int low = start;
			int high = end-1;
			
			while(low < high) {
				while(low < high && arr[pivot] <= arr[low]) {
					low++;
				}
				
				while(low < high && arr[high] <= arr[pivot]) {
					high--;
				}
				
				if(low < high) {
					swap(arr, low, high);
				}
			}
			
			if(arr[pivot] > arr[high]) {
				swap(arr, pivot, high);
				return high;
			}
			
			return pivot;
		}
		
		private void quicksort(int[] arr, int start, int end) {
			if(start < end) {
				int pivot = partition(arr, start, end);
				quicksort(arr, start, pivot-1);
				quicksort(arr, pivot+1, end);
			}
		}
		
		private void print() throws IOException {
			int len = arr.length;
			for(int i=0; i<len; i++) {
				bw.write(String.valueOf(arr[i])+"\n");
			}
		}
		
		public void run() throws IOException {
			input();
			quicksort(arr, 0, arr.length-1);
			print();
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

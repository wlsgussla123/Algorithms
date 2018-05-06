package algo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		int[] arr = {76, 6, 1, 5, 2, 4, 34, 100, 0};
		int[] temp = Arrays.copyOf(arr, arr.length);
		Bubblesort.run(temp);
		temp = Arrays.copyOf(arr, arr.length);
		Selectionsort.run(temp);
		temp = Arrays.copyOf(arr, arr.length);
		Insertionsort.run(temp);
		temp = Arrays.copyOf(arr, arr.length);
		Quicksort.run(temp);
		temp = Arrays.copyOf(arr, arr.length);
		Mergesort.run(temp);
		temp = Arrays.copyOf(arr, arr.length);
		new Heapsort().run(temp);
		temp = Arrays.copyOf(arr, arr.length);
		Countsort.run(temp);
		temp = Arrays.copyOf(arr, arr.length);
		Radixsort.run(temp);
		temp = Arrays.copyOf(arr, arr.length);
		Shellsort.run(temp);
	}
	
	static class Bubblesort {
		public static void swap(int[] arr, int a, int b) {
			int temp = arr[a];
			arr[a] = arr[b];
			arr[b] = temp;
		}
		
		public static void bubblesort(int[] arr) {
			int len = arr.length;
			for(int i=0; i<len; i++) {
				for(int j=0; j<len-i-1; j++) {
					if(arr[j] > arr[j+1]) {
						swap(arr, j, j+1);
					}
				}
			}
		}
	
		public static void run(int[] arr) {
			bubblesort(arr);
			print(arr);
		}
		
		public static void print(int[] arr) {
			System.out.print("bubble sort : ");
			int len = arr.length;
			for(int i=0; i<len; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}
	
	static class Selectionsort {
		public static void swap(int[] arr, int a, int b) {
			int temp = arr[a];
			arr[a] = arr[b];
			arr[b] = temp;
		}
		
		public static void selectionsort(int[] arr) {
			int len = arr.length;
			int min = -1;
			for(int i=0; i<len; i++) {
				min = i;
				for(int j=i+1; j<len; j++) {
					if(arr[min] > arr[j]) {
						min = j;
					}
				}
				swap(arr, min, i);
			}
		}
		
		public static void run(int[] arr) {
			selectionsort(arr);
			print(arr);
		}
		
		public static void print(int[] arr) {
			System.out.print("selection sort : ");
			int len = arr.length;
			for(int i=0; i<len; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}
	
	static class Insertionsort {
		public static void swap(int[] arr, int a, int b) {
			int temp = arr[a];
			arr[a] = arr[b];
			arr[b] = temp;
		}
		
		public static void insertionsort(int[] arr) {
			// full-swap
//			int len = arr.length;
//			for(int i=0; i<len-1; i++) {
//				for(int j=i+1; j>0; j--) {
//					if(arr[j-1] > arr[j]) {
//						swap(arr, j-1, j);
//					}
//				}
//			}
			
			// half-swap
			int len = arr.length;
			int idx = -1;
			int temp = -1;
			for(int i=1; i<len; i++) {
				temp = arr[i];
				idx = i-1;
				while((idx>=0) && arr[idx] > temp) {
					arr[idx+1] = arr[idx];
					idx--;
				}
				arr[idx+1] = temp;
			}
		}
		
		public static void run(int[] arr) {
			insertionsort(arr);
			print(arr);
		}
		
		public static void print(int[] arr) {
			System.out.print("insertion sort : ");
			int len = arr.length;
			for(int i=0; i<len; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}
	
	static class Heapsort {
		class Heap {
			int[] datas;
			int idx = 0;
			int size = 0;
			
			public Heap(int size) {
				datas = new int[size+1];
				this.size = size;
				idx = 0;
			}
			
			public void swap(int a, int b) {
				int temp = datas[a];
				datas[a] = datas[b];
				datas[b] = temp;
			}
			
			public void pushSwap(int cur) {
				if(cur == 1) {
					return;
				}
				
				if(datas[cur] < datas[cur/2]) {
					swap(cur, cur/2);
					pushSwap(cur/2);
				}
			}
			
			public void push(int data) {
				if(isFull()) {
					System.out.println("heap is full");
					return;
				}
				
				datas[++idx] = data;
				pushSwap(idx);
			}
			
			public void popSwap(int cur) {
				if(cur * 2 <= idx && cur * 2 + 1 <= idx) {
					if(datas[cur*2] < datas[cur*2+1] && datas[cur*2] < datas[cur]) {
						swap(cur*2, cur);
						popSwap(cur*2);
					} else if(datas[cur*2+1] < datas[cur*2] && datas[cur*2+1] < datas[cur]) {
						swap(cur*2+1, cur);
						popSwap(cur*2+1);
					}
				} else if(cur * 2 <= idx) {
					if(datas[cur*2] < datas[cur]) {
						swap(cur*2, cur);
						popSwap(cur*2);
					}
				}
			}
			
			public int pop() {
				if(isEmpty()) {
					System.out.println("heap is empty");
					return -1;
				}
				
				int del = datas[1];
				swap(1, idx--);
				popSwap(1);
				
				return del;
			}
			
			public boolean isEmpty() {
				return idx == 0;
			}
			
			public boolean isFull() {
				return idx == size;
			}
		}
		
		private Heap heap = null;
		
		public void run(int[] arr) {
			int len = arr.length;
			heap = new Heap(len);
			for(int i=0; i<len; i++) {
				heap.push(arr[i]);
			}
			
			for(int i=0; i<len; i++) {
				arr[i] = heap.pop();
			}
			
			print(arr);
		}
		
		public static void print(int[] arr) {
			System.out.print("heap sort : ");
			int len = arr.length;
			for(int i=0; i<len; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}
	
	static class Mergesort {
		public static void merge(int[] arr, int start, int mid, int end) {
			int lIdx = start;
			int rIdx = mid + 1;
			int idx = start;
			int[] sortArr = new int[arr.length];
			
			while(lIdx <= mid && rIdx <= end) {
				while(lIdx <= mid && arr[lIdx] <= arr[rIdx]) {
					sortArr[idx++] = arr[lIdx++];
				}
				
				while(rIdx <= end && arr[rIdx] <= arr[lIdx]) {
					sortArr[idx++] = arr[rIdx++];
				}
			}
			
			if(lIdx <= mid) {
				for(int i=lIdx; i<=mid; i++) {
					sortArr[idx++] = arr[i];
				}
			} else {
				for(int i=rIdx; i<=end; i++) {
					sortArr[idx++] = arr[i];
				}
			}
			
			for(int i=start; i<=end; i++) {
				arr[i] = sortArr[i];
			}
		}
		
		public static void mergesort(int[] arr, int start, int end) {
			if(start < end) {
				int mid = (start + end) / 2;
				mergesort(arr, start, mid);
				mergesort(arr, mid+1, end);
				merge(arr, start, mid, end);
			}
		}
		
		public static void run(int[] arr) {
			mergesort(arr, 0, arr.length-1);
			print(arr);
		}
		
		public static void print(int[] arr) {
			System.out.print("merge sort : ");
			int len = arr.length;
			for(int i=0; i<len; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}
	
	static class Quicksort {
		public static int getPivot(int[] arr, int start, int end) {
			int mid = (start + end) / 2;
			int pivot = arr[start] > arr[end] ? start : end;
			pivot = arr[pivot] > arr[mid] ? mid : pivot;
			return pivot;
		}
		
		public static void swap(int[] arr, int a, int b) {
			int temp = arr[a];
			arr[a] = arr[b];
			arr[b] = temp;
		}
		
		public static int quicksort(int[] arr, int start, int end) {
			int pivot = getPivot(arr, start, end);
			swap(arr, pivot, end);
			pivot = end;
			int low = start;
			int high = end-1;
			
			while(low < high) {
				while(low < high && arr[low] <= arr[pivot]) {
					low++;
				}
				
				while(low < high && arr[high] >= arr[pivot]) {
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
		
		public static void partition(int[] arr, int start, int end) {
			if(start < end) {
				int pivot = quicksort(arr, start, end);
				partition(arr, start, pivot-1);
				partition(arr, pivot+1, end);
			}
		}
		
		public static void run(int[] arr) {
			partition(arr, 0, arr.length-1);
			print(arr);
		}
		
		public static void print(int[] arr) {
			System.out.print("quick sort : ");
			int len = arr.length;
			for(int i=0; i<len; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}
	
	static class Countsort {
		public static void countsort(int[] arr) {
			int max = getMax(arr);
			int[] count = new int[max+1];
			int len = arr.length;
			int[] res = new int[len];
			
			for(int i=0; i<len; i++) {
				count[arr[i]]++;
			}
			
			for(int i=0; i<max; i++) {
				count[i+1] += count[i];
			}
			
			for(int i=len-1; i>=0; i--) {
				res[count[arr[i]]-1] = arr[i];
			}
			
			for(int i=0; i<len; i++) {
				arr[i] = res[i];
			}
		}
		
		public static int getMax(int[] arr) {
			int len = arr.length;
			int max = 0;
			for(int i=0; i<len; i++) {
				if(max < arr[i]) {
					max = arr[i];
				}
			}
			
			return max;
		}
		
		public static void run(int[] arr) {
			countsort(arr);
			print(arr);
		}
		
		public static void print(int[] arr) {
			System.out.print("counting sort : ");
			int len = arr.length;
			for(int i=0; i<len; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}
	
	static class Radixsort {
		public static int getMax(int[] arr) {
			int len = arr.length;
			int max = 0;
			for(int i=0; i<len; i++) {
				if(max < arr[i]) {
					max = arr[i];
				}
			}
			return max;
		}
		
		// 계수 정렬 기반
		public static void countsort(int[] arr, int exp, int max) {
			int[] count = new int[10];
			int len = arr.length;
			int[] res = new int[len];
			
			for(int i=0; i<len; i++) {
				count[(arr[i]/exp)%10]++;
			}
			
			for(int i=0; i<9; i++) {
				count[i+1] += count[i];
			}
			
			for(int i=len-1; i>=0; i--) {
				res[count[(arr[i]/exp)%10]-1] = arr[i];
				count[(arr[i]/exp)%10]--;
			}
			
			for(int i=0; i<len; i++) {
				arr[i] = res[i];
			}
		}
		
		// 리스트 기반
		public static void sort(int[] arr, int exp) {
			int len = arr.length;
			ArrayList<ArrayList<Integer>> list = new ArrayList();
			for(int i=0; i<10; i++) {
				list.add(new ArrayList());
			}
			
			for(int i=0; i<len; i++) {
				int pos = (arr[i]/exp)%10;
				list.get(pos).add(arr[i]);
			}
			
			int idx = 0;
			for(int i=0; i<len; i++) {
				for(int num : list.get(i)) {
					arr[idx++] = num;
				}
			}
		}
		
		public static void radixsort(int[] arr) {
			int max = getMax(arr);
			for(int exp=1; (max/exp) > 0; exp *= 10) {
//				countsort(arr, exp, max);
				sort(arr, exp);
			}
		}
		
		public static void run(int[] arr) {
			radixsort(arr);
			print(arr);
		}
		
		public static void print(int[] arr) {
			System.out.print("radix sort : ");
			int len = arr.length;
			for(int i=0; i<len; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}
	
	static class Shellsort {
		public static void gapsort(int[] arr, int gap) {
			int len = arr.length;
			int temp = 0;
			for(int i=gap; i<len; i+=gap) {
				temp = arr[i];
				int j = -1;
				for(j=i-gap; j>=0; j-=gap) {
					if(arr[j] > temp) {
						arr[j+gap] = arr[j];
					} else {
						break;
					}
				}
				arr[j+gap] = temp;
			}
		}
		
		public static void shellsort(int[] arr) {
			int gap = arr.length/2;
			while(gap>=1) {
				gapsort(arr, gap);
				gap/=2;
			}
		}
		
		public static void run(int[] arr) {
			shellsort(arr);
			print(arr);
		}
		
		public static void print(int[] arr) {
			System.out.print("shell sort : ");
			int len = arr.length;
			for(int i=0; i<len; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}
}

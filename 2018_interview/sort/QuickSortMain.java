package datastructure;

public class QuickSortMain {
	public static void main(String[] args) {
		int[] arr = {3,1,4,5,2};
		QuickSort.quicksort(arr, 0, arr.length-1);
		print(arr);
	}
	
	public static void print(int[] arr) {
		int len = arr.length;
		for(int i=0; i<len; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
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
			System.out.println(arr[pivot]);
			
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
}

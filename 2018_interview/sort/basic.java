package datastructure;

public class BasicSortMain {
	public static void bubbleSort(int[] arr) {
		int len = arr.length;
		int temp = 0;
		for(int i=0; i<len; i++) {
			for(int j=1; j<len-i; j++) {
				if(arr[j-1] > arr[j]) {
					temp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = temp;
				}
			}
		}
	}
	
	public static void selectionSort(int[] arr) {
		int len = arr.length;
		int min = 0;
		int temp = 0;
		for(int i=0; i<len; i++) {
			min = i;
			for(int j=i; j<len; j++) {
				if(arr[min] > arr[j]) {
					min = j;
				}
			}
			
			temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}
	}
	
	public static void insertionSort(int[] arr) {
		int len = arr.length;
		int temp = 0;
		for(int i=1; i<len; i++) {
			for(int j=i; j>0; j--) {
				if(arr[j-1] > arr[j]) {
					temp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = temp;
				} else {
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {71,2,3,81,911};
		bubbleSort(arr);
//		selectionSort(arr);
//		insertionSort(arr);
		print(arr);
	}
	
	public static void print(int[] arr) {
		int len = arr.length;
		for(int i=0; i<len; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}

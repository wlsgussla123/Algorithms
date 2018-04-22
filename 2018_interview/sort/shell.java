package datastructure;

public class ShellSortMain {
	public static void main(String[] args) {
		int[] arr = {5,2,592,87,55,124};
		ShellSort.shellSort(arr);
		print(arr);
	}
	
	private static void print(int[] arr) {
		for(int n : arr) {
			System.out.print(n + " ");
		}
		System.out.println();
	}
	
	static class ShellSort {
		public static void shellSort(int[] arr) {
			int gap = 0;
			int len = arr.length;
			
			gap = len/2;
			while(gap >= 1) {
				for(int i=0; i<gap; i++) {
					gapSort(arr, i, len-1, gap);
				}
				gap/=2;
			}
		}
		
		private static void gapSort(int[] arr, int begin, int end, int gap) {
			int temp=0;
			int j=0;
			
			for(int i = begin+gap; i<=end; i+=gap) {
				temp = arr[i];
				for(j = i-gap; j>=begin && temp < arr[j]; j-=gap) {
					arr[j+gap] = arr[j];
				}
				arr[j+gap] = temp;
			}
		}
	}
}

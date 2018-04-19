package datastructure;

import java.util.ArrayList;

public class RadixSortMain {
	public static int getMax(int[] arr) {
		int max = arr[0];
		int len = arr.length;
		for(int i=1; i<len; i++) {
			if(max < arr[i]) max = arr[i];
		}
		
		return max;
	}
	
	public static void radixSort(int[] arr) {
		int max = getMax(arr);
		ArrayList<ArrayList<Integer>> list = new ArrayList();
		for(int i=0; i<10; i++) list.add(new ArrayList());
		
		int len = arr.length;
		for(int exp=1; max/exp > 0; exp*=10) {
			for(int i=0; i<len; i++) {
				int bucket = (arr[i]/exp)%10;
				list.get(bucket).add(arr[i]);
			}
			
			int pos = 0;
			for(int i=0; i<10; i++) {
				for(int j=0; j<list.get(i).size(); j++) {
					arr[pos++] = list.get(i).get(j);
				}
			}
			
			for(int i=0; i<10; i++) list.get(i).clear();
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {91, 2, 718, 1002, 102, 0};
		radixSort(arr);
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

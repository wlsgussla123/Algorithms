package datastructure;

class MaxHeap {
	private int[] datas;
	private int idx = 0;
	private int size = 0;
		
	public MaxHeap(int size) {
		this.size = size;
		this.datas = new int[size+1];
	}
	
	private void pushSwap(int cur) {
		if(cur == 1) {
			return;
		}
		
		if(datas[cur/2] < datas[cur]) {
			swap(cur/2, cur);
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
	
	private void popSwap(int cur) {
		if(cur * 2 <= idx && cur * 2 + 1 <= idx) {
			if(datas[cur*2] < datas[cur*2+1] && datas[cur] < datas[cur*2+1]) {
				swap(cur*2+1, cur);
				popSwap(cur*2+1);
			} else if(datas[cur*2+1] < datas[cur*2] && datas[cur] < datas[cur*2]) {
				swap(cur*2, cur);
				popSwap(cur*2);
			}
		} else if(cur * 2 <= idx && datas[cur] < datas[cur*2]) {
			swap(cur, cur*2);
			popSwap(cur*2);
		}
	}
	
	public Integer pop() {
		if(isEmpty()) {
			System.out.println("heap is empty");
			return null;
		}
		
		Integer del = datas[1]; 
		datas[1] = datas[idx--];
		popSwap(1);
		
		return del;
	}
	
	public Integer peek() {
		if(isEmpty()) {
			System.out.println("heap is empty");
			return null;
		}
		return datas[1];
	}
	
	private boolean isEmpty() {
		return idx == 0;
	}
	
	private boolean isFull() {
		return idx == size;
	}
	
	private void swap(int a, int b) {
		int temp = datas[a];
		datas[a] = datas[b];
		datas[b] = temp;
	}
}

public class HeapMain {
	public static void main(String[] args) {
		int[] arr = {2,5,1,3,4};
		heapSort(arr);
		print(arr);
	}
	
	public static void heapSort(int[] arr) {
		int len = arr.length;
		MaxHeap mh = new MaxHeap(len);
		for(int i=0; i<len; i++) {
			mh.push(arr[i]);
		}
		
		for(int i=len-1; i>=0; i--) {
			arr[i] = mh.pop();
		}
	}
	
	private static void print(int[] arr) {
		for(Integer n : arr) {
			System.out.print(n + " ");
		}
		System.out.println();
	}
}

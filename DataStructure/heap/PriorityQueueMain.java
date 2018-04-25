package datastructure;

class Node {
	public int data;
	public int priority;
	public Node next;
	public Node(int data, int p) {
		this.data = data;
		this.priority = p;
		this.next = null;
	}
}

class PriorityQueueHeap {
	private Node[] datas;
	private int idx = 0;
	private int size = 0;
		
	public PriorityQueueHeap(int size) {
		this.size = size;
		this.datas = new Node[size+1];
	}
	
	private void pushSwap(int cur) {
		if(cur == 1) {
			return;
		}
		
		if(datas[cur/2].priority < datas[cur].priority) {
			swap(cur/2, cur);
			pushSwap(cur/2);
		}
	}
	
	public void push(int data, int priority) {
		if(isFull()) {
			System.out.println("heap is full");
			return;
		}
		datas[++idx] = new Node(data, priority);
		pushSwap(idx);
	}
	
	private void popSwap(int cur) {
		if(cur * 2 <= idx && cur * 2 + 1 <= idx) {
			if(datas[cur*2].priority < datas[cur*2+1].priority && datas[cur].priority < datas[cur*2+1].priority) {
				swap(cur*2+1, cur);
				popSwap(cur*2+1);
			} else if(datas[cur*2+1].priority < datas[cur*2].priority && datas[cur].priority < datas[cur*2].priority) {
				swap(cur*2, cur);
				popSwap(cur*2);
			}
		} else if(cur * 2 <= idx && datas[cur].priority < datas[cur*2].priority) {
			swap(cur, cur*2);
			popSwap(cur*2);
		}
	}
	
	public Integer pop() {
		if(isEmpty()) {
			System.out.println("heap is empty");
			return null;
		}
		
		Integer del = datas[1].data; 
		datas[1] = datas[idx--];
		popSwap(1);
		
		return del;
	}
	
	public Integer peek() {
		if(isEmpty()) {
			System.out.println("heap is empty");
			return null;
		}
		return datas[1].data;
	}
	
	public boolean isEmpty() {
		return idx == 0;
	}
	
	private boolean isFull() {
		return idx == size;
	}
	
	private void swap(int a, int b) {
		Node temp = datas[a];
		datas[a] = datas[b];
		datas[b] = temp;
	}
}

public class PriorityQueueMain {
	public static void main(String[] args) {
		PriorityQueueHeap pq = new PriorityQueueHeap(10);
		pq.push(2, 1);
		pq.push(3, 2);
		pq.push(4, 3);
		pq.push(1, 0);
		
		while(!pq.isEmpty()) {
			System.out.println(pq.pop());
		}
	}
}

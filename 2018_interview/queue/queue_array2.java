package datastructure;

class Queue {
	private int[] datas;
	private int size;
	private int front = 0, rear = 0;
	
	public Queue(int size) {
		datas = new int[size];
		this.size = size;
	}
	
	public void enqueue(int data) {
		if(isFull()) {
			System.out.println("queue is full");
			return;
		}
			
		datas[(rear+1)%size] = data;
		rear = (rear+1) % size;
	}
	
	public int dequeue() {
		if(isEmpty()) {
			System.out.println("queue is empty");
			return -1;
		}
		
		int del = datas[(front+1)%size];
		front = (front + 1) % size;
		return del;
	}
	
	public int peek() {
		if(isEmpty()) {
			System.out.println("queue is empty");
			return -1;
		}
		return datas[(front+1)%size];
	}
	
	public boolean isEmpty() {
		return rear == front;
	}
	
	public boolean isFull() {
		return (rear+1)%size == front;
	}
}

public class QueueMain {
	public static void main(String[] args) {
		Queue q = new Queue(4);
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		q.enqueue(4);
		q.enqueue(3);
		q.enqueue(2);
		q.enqueue(1);
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		
	}
}

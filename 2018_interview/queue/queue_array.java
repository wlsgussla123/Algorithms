package datastructure;

import java.util.EmptyStackException;

class Queue {
	private int front, rear;
	private Object[] datas;
	private int size;
	
	public Queue(int n) {
		this.front = 0;
		this.rear = 0;
		this.size = n+1;
		this.datas = new Object[size+1];
	}
	
	public void enqueue(Object data) {
		if(isFull()) {
			throw new IllegalStateException();
		}
		
		datas[(rear+1)%size] = data;
		rear = (rear+1)%size;
	}
	
	public Object dequeue() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		
		Object data = datas[(front+1)%size];
		front = (front+1)%size;
		return data;
	}
	
	public Object peek() {
		return datas[(front+1)%size];
	}
	
	public boolean isFull() {
		return (rear+1) % size == front;
	}
	
	public boolean isEmpty() {
		return front == rear;
	}
}

public class QueueMain {
	public static void main(String[] args) {
		Queue q = new Queue(3);
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		System.out.println(q.peek());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
	}
}

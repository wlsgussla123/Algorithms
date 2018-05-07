package datastructure;

class Queue {
	class Node {
		int data;
		Node next;
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}
	
	private Node front = null, rear = null;
	public Queue() {
		this.front = null;
		this.rear = null;
	}
	
	public void enqueue(int data) {
		Node newNode = new Node(data);
		if(front == null) {
			front = newNode;
			rear = newNode;
			return;
		}
		
		rear.next = newNode;
		rear = newNode;
	}
	
	public int dequeue() {
		if(isEmpty()) {
			System.out.println("queue is empty");
			return -1;
		}
		int del = front.data;
		front = front.next;
		if(front == null) {
			rear = null;
		}
		return del;
	}
	
	public int peek() {
		if(isEmpty()) {
			System.out.println("queue is empty");
			return -1;
		}
		
		return front.data;
	}
	
	public boolean isEmpty() {
		return front == null;
	}
}

public class QueueMain {
	public static void main(String[] args) {
		Queue q = new Queue();
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

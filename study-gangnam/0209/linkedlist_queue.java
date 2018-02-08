package algo;

class LLQueue {
	private class Node {
		private int data;
		private Node nextNode;
		
		private Node(int data) {
			this.data = data;
			this.nextNode = null;
		}
	}
	
	private Node front;
	private Node rear;
	
	public LLQueue() {
		this.front = null;
		this.rear = null;
	}
	
	public boolean isEmpty() {
		return front == null;
	}
	
	public void enQueue(int data) {
		Node newNode = new Node(data);
		
		if(isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			rear.nextNode = newNode;
			rear = newNode;
		}
	}
	
	public int deQueue() {
		if(isEmpty()) {
			System.out.println("Queue is Empty");
			return -1;
		}
		Node delNode = front;
		front = front.nextNode;
		if(front == null) {
			rear = null;
		}
	
		return delNode.data;
	}
	
	public int peek() {
		if(isEmpty()) {
			System.out.println("Queue is Empty");
			return -1;
		}
		return front.data;
	}
	
	public void print() {
		if(isEmpty()) System.out.println("Queue is Empty");
		Node cur = front;
		while(cur!=null) {
			System.out.print(cur.data + " ");
			cur = cur.nextNode;
		}
		System.out.println();
	}
}

public class Main {
	public static void main(String[] args) {
		LLQueue q = new LLQueue();
		q.enQueue(1);
		q.enQueue(2);
		q.enQueue(3);
		q.enQueue(4);
		q.enQueue(5);
		q.print();
		q.deQueue();
		q.deQueue();
		q.print();
	}
}

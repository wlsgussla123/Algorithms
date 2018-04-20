package datastructure;

class Queue {
	private class Node {
		private Object data;
		private Node next;
		public Node(Object data) {
			this.data = data;
			this.next = null;
		}
	}
	
	private Node front, rear;
	public Queue() {
		this.front = null;
		this.rear = null;
	}
	
	public void enqueue(Object data) {
		Node newNode = new Node(data);
		if(isEmpty()) {
			this.front = newNode;
			this.rear = newNode;
			return;
		}
		
		rear.next = newNode;
		rear = newNode;
	}
	
	public Object dequeue() {
		if(isEmpty()) {
			return null;
		}
		Object data = front.data;
		if(front.next == null) {
			front = null;
			rear = null;
			return data;
		} else {
			front = front.next;
			return data;
		}
	}
	
	public Object peek() {
		if(isEmpty()) return null;
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
		System.out.println(q.peek());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
	}
}

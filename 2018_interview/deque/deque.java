package datastructure;

import java.util.EmptyStackException;

class Deque {
	private class Node {
		private Node prev;
		private Node next;
		private Object data;
		
		public Node(Object data) {
			this.data = data;
			this.prev = null;
			this.next = null;
		}
	}
	
	private Node front, rear;
	private int size;
	public Deque() {
		this.front = null;
		this.rear = null;
		this.size = 0;
	}
	
	public void pushFront(Object data) {
		Node newNode = new Node(data);
		size++;
		if(isEmpty()) {
			front = newNode;
			rear = newNode;
			return;
		}
		
		front.prev = newNode;
		newNode.next = front;
		front = newNode;
	}
	
	public void pushBack(Object data) {
		Node newNode = new Node(data);
		size++;
		if(isEmpty()) {
			front = newNode;
			rear = newNode;
			return;
		}
		
		rear.next = newNode;
		newNode.prev = rear;
		rear = newNode;
	}
	
	public Object popFront() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		
		Object data = front.data;
		size--;
		if(front.next == null) {
			front = null;
			rear = null;
			return data;
		}
		front = front.next;
		front.prev = null;
		
		return data;
	}
	
	public Object popBack() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		
		Object data = rear.data;
		size--;
		
		if(rear.prev == null) {
			front = null;
			rear = null;
			return data;
		}
		
		rear = rear.prev;
		rear.next = null;
		return data;
	}
	
	public Object getFront() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		
		return front.data;
	}
	
	public Object getBack() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		
		return rear.data;
	}
	
	public boolean isEmpty() {
		return front == null;
	}
	
	public int getSize() {
		return size;
	}
}

public class DequeMain {
	public static void main(String[] args) {
		Deque dq = new Deque();
		dq.pushFront(2);
		dq.pushFront(1);
		dq.pushBack(3);
		dq.pushBack(4);
		System.out.println(dq.getFront());
		System.out.println(dq.getBack());
		System.out.println(dq.popFront());
		System.out.println(dq.popBack());
		System.out.println(dq.popFront());
		System.out.println(dq.popBack());
	}
}

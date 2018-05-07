package datastructure;

import java.util.EmptyStackException;

class Stack {
	private class Node {
		private int data;
		private Node next;
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}
	
	private Node top;
	public Stack() {
		this.top = null;
	}
	
	public void push(int data) {
		Node newNode = new Node(data);
		newNode.next = top;
		top = newNode;
	}
	
	public int pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		
		int del = top.data;
		top = top.next;
		return del;
	}
	
	public int peek() {
		return top.data;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
}

class Deque {
	private Stack front = null;
	private Stack rear = null;
	
	public Deque() {
		this.front = new Stack();
		this.rear = new Stack();
	}
	
	public void pushFront(int data) {
		front.push(data);
	}
	
	public void pushBack(int data) {
		rear.push(data);
	}
	
	public int getFront() {
		return front.peek();
	}
	
	public int getBack() {
		return rear.peek();
	}
	
	public int popFront() {
		return front.pop();
	}
	
	public int popBack() {
		return rear.pop();
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

package algo;

import java.util.EmptyStackException;

class Stack {
	private class StackNode {
		private int data;
		private StackNode next;
		public StackNode(int data) {
			this.data = data;
			this.next = null;
		}
	}
	private StackNode top;

	public Stack() {
		this.top = null;
	}
	public void push(int data) {
		StackNode newNode = new StackNode(data);
		if(isEmpty()) {
			top = newNode;
		} else {
			newNode.next = top;
			top = newNode;
		}
	}
	
	public int peek() {
		if(isEmpty()) throw new EmptyStackException();
		return top.data;
	}
	
	public int pop() {
		if(isEmpty()) throw new EmptyStackException();
		int data = top.data;
		top = top.next;
		return data;
	}
	
	public boolean isEmpty() {
		return this.top == null;
	}
}

class QueueByStack {
	private Stack s1;
	private Stack s2;
	public QueueByStack() {
		this.s1 = new Stack();
		this.s2 = new Stack();
	}
	
	public void enQueue(int data) {
		s1.push(data);
	}
	
	public int deQueue() {
		while(!s1.isEmpty()) {
			s2.push(s1.pop());
		}
		
		int data = s2.pop();
		while(!s2.isEmpty()) {
			s1.push(s2.pop());
		}
		
		return data;
	}
}

public class StackMain {
	public static void main(String[] args) {
		QueueByStack qbs = new QueueByStack();
		qbs.enQueue(1);
		qbs.enQueue(2);
		qbs.enQueue(3);
		System.out.println(qbs.deQueue());
		System.out.println(qbs.deQueue());
		System.out.println(qbs.deQueue());
	}
}

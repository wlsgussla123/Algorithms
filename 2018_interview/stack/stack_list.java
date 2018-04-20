package datastructure;

import java.util.EmptyStackException;

class Stack {
	private class Node {
		private Object data;
		private Node next;
		public Node(Object data) {
			this.data = data;
			this.next = null;
		}
	}
	
	private Node top;
	public Stack() {
		this.top = null;
	}
	
	public void push(Object data) {
		Node newNode = new Node(data);
		newNode.next = top;
		top = newNode;
	}
	
	public Object pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		
		Object del = top.data;
		top = top.next;
		return del;
	}
	
	public Object peek() {
		return top.data;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
}

public class StackMain {
	public static void main(String[] args) {
		Stack s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		System.out.println(s.peek());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
	}
}

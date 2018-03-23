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
			if(top.data < newNode.data) {
				StackNode cur = top;
				StackNode prev = top;
				while(cur != null) {
					if(cur.data < newNode.data) {
						prev = cur;
						cur = cur.next;
					} else {
						break;
					}
				}
				newNode.next = prev.next;
				prev.next = newNode;
			} else {
				newNode.next = top;
				top = newNode;
			}
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

public class StackMain {
	public static void main(String[] args) {
		Stack s = new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		while(!s.isEmpty()) {
			System.out.println(s.pop());
		}
		s.push(4);
		s.push(3);
		s.push(2);
		s.push(1);
		while(!s.isEmpty()) {
			System.out.println(s.pop());
		}
		s.push(3);
		s.push(1);
		s.push(5);
		s.push(2);
		s.push(4);
		s.push(6);
		while(!s.isEmpty()) {
			System.out.println(s.pop());
		}
	}
}

package algo;

import java.io.IOException;
import java.util.EmptyStackException;

class Stack<T> {
	private class StackNode {
		private T data;
		private StackNode next;
		public StackNode(T data) {
			this.data = data;
		}
	}
	private StackNode top = null;
	
	public void push(T data) {
		StackNode node = new StackNode(data);
		if(top == null) {
			top = node;
		} else {
			node.next = top;
			top = node;
		}
	}
	
	public T pop() {
		if(isEmpty()) throw new EmptyStackException();
		StackNode del = top;
		T data = del.data;
		top = top.next;
		
		return data;
	}
	
	public T peek() {
		if(isEmpty()) throw new EmptyStackException();
		return top.data;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Stack<Integer> stack = new Stack<Integer>();
    	stack.push(1);
    	stack.push(2);
    	stack.push(3);
    	System.out.println(stack.pop());
    	stack.push(4);
    	System.out.println(stack.pop());
    	System.out.println(stack.pop());
    	System.out.println(stack.pop());
    }
}

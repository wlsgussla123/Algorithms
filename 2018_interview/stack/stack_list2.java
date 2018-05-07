package datastructure;

class Stack<T> {
	class Node {
		T data;
		Node next;
		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}
	
	private Node top = null;
	public Stack() {
		top = null;
	}
	
	public void push(T data) {
		Node newNode = new Node(data);
		newNode.next = top;
		top = newNode;
	}
	
	public T pop() {
		if(isEmpty()) return null;
 		T del = top.data;
		top = top.next;
		return del;
	}
	
	public T peek() {
		if(isEmpty()) return null;
		return top.data;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
}

public class StackMain {
	public static void main(String[] args) {
		Stack<Character> s = new Stack();
		// 4 4 3 2 1
		s.push('a');
		s.push('b');
		s.push('c');
		s.push('d');
		System.out.println(s.peek());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		s.push('e');
		System.out.println(s.peek());
		System.out.println(s.pop());
	}
}

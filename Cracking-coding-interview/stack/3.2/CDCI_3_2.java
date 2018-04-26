package coding_interview;

class Stack {
	private class Node {
		int data;
		Node next;
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
		if(isEmpty()) {
			top = newNode;
			return;
		}
		
		if(top.data > data) {
			newNode.next = top;
			top = newNode;
		} else {
			Node cur = top;
			Node prev = top;
			while(cur != null) {
				if(cur.data < data) {
					prev = cur;
					cur = cur.next;
				} else {
					prev.next = newNode;
					newNode.next = cur;
					break;
				}
			}
			
			if(cur == null) {
				prev.next = newNode;
			}
		}
	}
	
	public Integer pop() {
		if(isEmpty()) {
			System.out.println("stack is empty");
			return null;
		}
		
		Integer del = top.data;
		top = top.next;
		return del;
	}
	
	public Integer peek() {
		if(isEmpty()) {
			System.out.println("stack is empty");
			return null;
		}
		
		return top.data;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
}

public class CDCI_3_2 {
	public static void main(String[] args) {
		Stack s = new Stack();
		s.push(5);
		s.push(4);
		s.push(3);
		s.push(2);
		s.push(1);
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
	}
}

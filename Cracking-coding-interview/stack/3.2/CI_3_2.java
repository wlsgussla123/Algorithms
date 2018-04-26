package coding_interview;

class Stack <T> {
	private class Node {
		T data;
		Node next;
		public Node(T data) {
			this.data = data;
			this.next = null;
		}
		
	}
	private Node top;
	public Stack() {
		this.top = null;
	}
	
	public void push(T data) {
		Node newNode = new Node(data);
		if(isEmpty()) {
			top = newNode;
			return;
		}
		
		newNode.next = top;
		top = newNode;
	}
	
	public T pop() {
		if(isEmpty()) {
			System.out.println("stack is empty");
			return null;
		}
		
		T del = top.data;
		top = top.next;
		return del;
	}
	
	public T peek() {
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

class MinStack extends Stack<Integer> {
	private Stack<Integer> s = new Stack<Integer>();
	
	public void push(int data) {
		if(s.isEmpty()) {
			s.push(data);
		} else {
			if(data <= min()) {
				s.push(data);
			}
		}
		super.push(data);
	}
	
	public Integer pop() {
		Integer del = super.pop();
		if(s.peek() == del) {
			s.pop();
		}
		
		return del;
	}
	
	public Integer min() {
		if(s.peek() == null) {
			return null;
		} else {
			return s.peek();
		}
	}
}

public class CDCI_3_2 {
	public static void main(String[] args) {
		MinStack ms = new MinStack();
		ms.push(5);
		ms.push(4);
		ms.push(1);
		ms.push(3);
		ms.push(2);
		
		while(!ms.isEmpty()) {
			System.out.println(ms.min());
			ms.pop();
		}
	}
}

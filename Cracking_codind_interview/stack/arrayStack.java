package algo;

class Stack {
	private int[] stack;
	private int top;
	private int MAX;
	
	public Stack(int MAX) {
		this.top = -1;
		this.MAX = MAX;
		this.stack = new int[MAX];
	}
	
	public boolean isFull() {
		return this.top == this.MAX;
	}
	
	public boolean isEmpty() {
		return this.top == -1;
	}
	
	public void push(int data) {
		if(!isFull()) {
			this.stack[++top] = data;			
		} else {
			System.out.println("Stack is full");
		}
	}
	
	public int pop() {
		if(!isEmpty()) {
			return this.stack[top--];
		} else {
			System.out.println("Stack is empty");
			return -1;
		}
	}
	
	public int peek() {
		if(!isEmpty()) {
			return this.stack[top];
		} else {
			System.out.println("Stack is empty");
			return -1;
		}
	}
}

public class main {		
	public static void main(String args[]) {
		Stack stack = new Stack(100);
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
		System.out.println(stack.peek());
		stack.pop();
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
	}
}

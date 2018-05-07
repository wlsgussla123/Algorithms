package datastructure;

class Stack {
	private int[] datas;
	private int top = -1;
	private int size = 0;
	
	public Stack(int size) {
		this.size = size;
		datas = new int[size];
	}
	
	public void push(int data) {
		if(isFull()) {
			System.out.println("stack is full");
			return;
		}
		datas[++top] = data;
	}
	
	public int peek() {
		if(isEmpty()) {
			System.out.println("stack is empty");
			return -1;
		}
		return datas[top];
	}
	
	public int pop() {
		if(isEmpty()) {
			System.out.println("stack is empty");
			return -1;
		}
		
		int del = datas[top--];
		return del;
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	public boolean isFull() {
		return top == size-1;
	}
}

public class StackMain {
	public static void main(String[] args) {
		Stack s = new Stack(3);
		// 4 4 3 2 1
		s.push(3);
		s.push(2);
		s.push(1);
		s.push(4);
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		s.push(4);
		System.out.println(s.pop());
	}
}

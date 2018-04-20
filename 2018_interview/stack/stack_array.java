package datastructure;

import java.util.Arrays;
import java.util.EmptyStackException;

class Stack {
	private int top = -1;
	private int[] datas;
	private int size;
	
	public Stack(int n) {
		this.datas = new int[n];
		size = n;
	}
	
	public void push(Object data) {
		if(isFull()) {
			int[] oldData = Arrays.copyOf(datas, size);
			datas = new int[size*2];
			for(int i=0; i<size; i++) {
				datas[i] = oldData[i];
			}
		}
		datas[++top] = (Integer)data;
	}
	
	public Object peek() {
		return datas[top];
	}
	
	public Object pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		return datas[top--];
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
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		System.out.println(s.peek());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
	}
}

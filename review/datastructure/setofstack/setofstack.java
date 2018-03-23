package algo;

import java.util.Stack;

class SetOfStack extends Stack<Integer> {
	Stack<Stack<Integer>> setofstack = new Stack<>();
	int size;
	public SetOfStack(int size) {
		this.setofstack = new Stack<>();
		setofstack.push(new Stack<>());
		this.size = size;
	}
	
	public void push(int data) {
		if(setofstack.peek().size() == size) {
			setofstack.push(new Stack<>());
		}
		setofstack.peek().push(data);
	}
	
	public Integer peek() {
		int data = setofstack.peek().peek();
		return data;
	}
	
	public Integer pop() {
		if(setofstack.peek().size() == 0) {
			setofstack.pop();
		}
		int data = setofstack.peek().pop();
		return data;
	}
}

public class StackMain {
	public static void main(String[] args) {
		SetOfStack sos = new SetOfStack(3);
		sos.push(1);
		sos.push(2);
		sos.push(3);
		sos.push(4);
		sos.push(5);
		sos.push(6);
		sos.push(7);
		System.out.println(sos.pop());
		System.out.println(sos.pop());
		System.out.println(sos.pop());
		System.out.println(sos.pop());
		System.out.println(sos.pop());
		System.out.println(sos.pop());
		System.out.println(sos.pop());
	}
}

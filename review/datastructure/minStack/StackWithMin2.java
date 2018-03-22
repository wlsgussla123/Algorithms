package algo;

import java.util.EmptyStackException;
import java.util.Stack;

public class StackWithMin2 extends Stack<Integer> {
	Stack<Integer> s2;
	public StackWithMin2() {
		s2 = new Stack<Integer>();
	}
	
	public void push(int data) {
		if(data <= min()) {
			s2.push(data);
		}
		super.push(data);
	}
	
	public Integer pop() {
		if(s2.isEmpty()) throw new EmptyStackException();
		int data = super.pop();
		if(data == min()) {
			s2.pop();
		}
		
		return data;
	}
	
	public int min() {
		if(s2.isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			return s2.peek();
		}
	}
}

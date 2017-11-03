package algo;

public class main {		
	public static void main(String args[]) {
		MyStack<Integer> stack = new MyStack<>();
		
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

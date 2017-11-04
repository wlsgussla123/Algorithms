package algo;

import java.util.EmptyStackException;

class FixedMultiStack {
	private int numberOfStacks = 3;
	private int stackOfMaxSize;
	private int[] values;
	private int[] sizes; // 각 스택 사이즈를 측정하기 위한 배열
	
	public FixedMultiStack(int stackSize) {
		this.stackOfMaxSize = stackSize;
		this.values = new int[stackSize * this.numberOfStacks]; // 스택 사이즈 * 갯수만큼
		this.sizes = new int[numberOfStacks]; // 스택의 개수에 맞게 
	}
		
	public void push(int stackNum, int value) throws StackOverflowError {
		if(isFull(stackNum)) {
			throw new StackOverflowError();
		}
		
		this.sizes[stackNum]++; // 스택 number 증가시키고
		this.values[indexOfTop(stackNum)] = value;
	}
	
	public int pop(int stackNum) throws EmptyStackException {
		if(isEmpty(stackNum)) {
			throw new EmptyStackException();
		}
		
		int topIndex = indexOfTop(stackNum);
		int value = values[topIndex]; // 자리에 value 추가
		values[topIndex] = 0;
		sizes[stackNum]--; //지웠으니 사이즈 -1
		return value;
	}
	
	public int peek(int stackNum) {
		if(isEmpty(stackNum)) {
			throw new EmptyStackException();
		}
		return values[indexOfTop(stackNum)];
	}
	
	public boolean isFull(int stackNum) {
		return sizes[stackNum] == this.stackOfMaxSize; // stackNum을 통하여 확인한 스택이 차있는가?
	}
	
	public boolean isEmpty(int stackNum) {
		return sizes[stackNum] == 0;
	}

	// 스택의 꼭대기 값을 가리키는 인덱스 반환 (top 반환인데, stack이 여러개니까 사용하는거)
	private int indexOfTop(int stackNum) {
		int offset = stackNum * stackOfMaxSize;
		int size = sizes[stackNum];
		
		return offset + size - 1; // push에서 미리 size를 증가시키기 때문에 -1
	}
}

public class main {		
	public static void main(String args[]) {
		FixedMultiStack stack = new FixedMultiStack(3);
		
		stack.push(0, 1);
		stack.push(0, 2);
		stack.push(1, 3);
		stack.push(2, 4);
		System.out.println(stack.peek(0));
		System.out.println(stack.peek(1));
		System.out.println(stack.peek(2));
	}
}

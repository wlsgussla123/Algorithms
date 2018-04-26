package coding_interview;

class Stack {
	private Integer[] items;
	private int[] sizes;
	private int capacity;
	private final int numberOfStacks = 3;
	
	public Stack(int size) {
		capacity = size/3;
		items = new Integer[size * numberOfStacks];
		sizes = new int[numberOfStacks];
	}
	
	public void push(int idx, int item) {
		if(isFull(idx)) {
			System.out.println("stack" + idx + " " + "is full");
			return;
		}
		
		sizes[idx]++;
		items[topOfIndex(idx)] = item;
	}
	
	public Integer pop(int idx) {
		if(isEmpty(idx)) {
			System.out.println("stack is empty");
			return null;
		}
		
		Integer delItem = items[topOfIndex(idx)];
		sizes[idx]--;
		
		return delItem;
	}
	
	public Integer peek(int idx) {
		if(isEmpty(idx)) {
			System.out.println("stack is empty");
			return null;
		}

		return items[topOfIndex(idx)];
	}
	
	public boolean isEmpty(int idx) {
		return sizes[idx] == 0;
	}
	
	public boolean isFull(int idx) {
		return sizes[idx] == capacity;
	}
	
	public int topOfIndex(int idx) {
		int offset = idx * capacity + sizes[idx]; // 0, 3, 6
		return offset;
	}
}

public class CDCI_3_1 {
	public static void main(String[] args) {
		Stack s = new Stack(10);
		s.push(0, 1);
		s.push(0, 2);
		s.push(0, 3);
		s.push(1, 4);
		s.push(1, 5);
		s.push(1, 6);
		s.push(2, 7);
		s.push(2, 8);
		s.push(2, 9);
		s.push(2, 10);
		s.push(1, 11);
		s.push(0, 12);

		System.out.println(s.pop(2));
		System.out.println(s.pop(2));
		System.out.println(s.pop(2));
		System.out.println(s.pop(1));
		System.out.println(s.pop(1));
		System.out.println(s.pop(1));
		System.out.println(s.pop(0));
		System.out.println(s.pop(0));
		System.out.println(s.pop(0));
		System.out.println(s.pop(0));
		System.out.println(s.pop(1));
		System.out.println(s.pop(2));
		
		s.push(0, 1);
		s.push(0, 2);
		System.out.println(s.pop(0));
		System.out.println(s.pop(0));
		s.push(0, 1);
		s.push(0, 2);
		s.push(0, 3);
		System.out.println(s.pop(0));
		System.out.println(s.pop(0));
		System.out.println(s.pop(0));
	}
}

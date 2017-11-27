package line;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Queue {
	private List<Integer> values = new LinkedList<>();
	
	public void push(int item) {
		this.values.add(item);
	}
	
	public int pop() {
		if(this.isEmpty()) {
			return -1;
		}
		
		int data = this.values.get(0);
		this.values.remove(0);
		
		return data;
	}
	
	public int front() {
		if(this.isEmpty()) {
			return -1;
		}
		
		return this.values.get(0);
	}
	
	public int back() {
		if(this.isEmpty()) {
			return -1;
		}
		
		return this.values.get(this.values.size() - 1);
	}
	
	public boolean isEmpty() {
		return this.values.size() == 0;
	}

	public int getSize() {
		return this.values.size();
	}
}

class Task {
	private final Scanner sc = new Scanner(System.in);
	private int N;
	private String[] strs;
	private Queue queue = new Queue();
	
	public void run() {
		input();
		process();
	}
	
	private void input() {
		N = sc.nextInt();
		strs = new String[N];
		sc.nextLine(); // 개행처리
		
		for(int i=0; i<N; i++) {
			strs[i] = sc.nextLine();
		}
	}
	
	private void process() {
		String[] temp;
		
		for(int i=0; i<strs.length; i++) {
			temp = strs[i].split(" ");
			
			if(temp[0].equals("push")) {
				queue.push(Integer.parseInt(temp[1]));
			} else if(temp[0].equals("pop")) {
				System.out.println(queue.pop());
			} else if(temp[0].equals("front")) {
				System.out.println(queue.front());
			} else if(temp[0].equals("back")) {
				System.out.println(queue.back());
			} else if(temp[0].equals("size")) {
				System.out.println(queue.getSize());
			} else if(temp[0].equals("empty")) {
				if(queue.isEmpty()) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
			}
		}
	}
	
	private void print() {
		for(int i=0; i<N; i++) {
			System.out.println(strs[i]);
		}
	}
}

public class Main {
	public static void main(String args[]) {
		Task task = new Task();
		task.run();
	}
}

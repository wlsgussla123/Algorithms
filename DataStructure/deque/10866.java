package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Deque {
	private class Node {
		int data;
		Node prevNode;
		Node nextNode;
		public Node(int data) {
			this.data = data;
			this.prevNode = null;
			this.nextNode = null;
		}
	}
	
	private Node front;
	private Node rear;
	private int size;
	public Deque() {
		this.front = null;
		this.rear = null;
		this.size = 0;
	}
	
	public void pushFront(int x) {
		Node newNode = new Node(x);
		if(isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			newNode.nextNode = front;
			front.prevNode = newNode;
			front = newNode;
		}
		size++;
	}
	
	public void pushBack(int x) {
		Node newNode = new Node(x);
		if(isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			newNode.prevNode = rear;
			rear.nextNode = newNode;
			rear = newNode;
		}
		size++;
	}
	
	public int popFront() {
		if(isEmpty()) {
			return -1;
		} else {
			int data = front.data;
			front = front.nextNode;
			if(front==null) {
				rear = null;
			} else {
				front.prevNode = null;
			}
			size--;
			return data;
		}
	}
	
	public int popBack() {
		if(isEmpty()) {
			return -1;
		} else {
			int data = rear.data;
			rear = rear.prevNode;
			if(rear==null) {
				front = null;
			} else {
				rear.nextNode = null;
			}
			size--;
			return data;
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty() {
		return (front == null);
	}
	
	public int getFront() {
		if(isEmpty()) return -1;
		else return front.data;
	}
	
	public int getBack() {
		if(isEmpty()) return -1;
		else return rear.data;
	}
}

class Task {
	private int N;
	private Deque dq = new Deque();
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			String op = st.nextToken();
			
			if(op.equals("push_front")) {
				int data = Integer.parseInt(st.nextToken());
				dq.pushFront(data);
			} else if(op.equals("push_back")) {
				int data = Integer.parseInt(st.nextToken());
				dq.pushBack(data);
			} else if(op.equals("pop_front")) {
				System.out.println(dq.popFront());
			} else if(op.equals("pop_back")) {
				System.out.println(dq.popBack());
			} else if(op.equals("size")) {
				System.out.println(dq.getSize());
			} else if(op.equals("empty")) {
				if(dq.isEmpty())
					System.out.println("1");
				else
					System.out.println("0");
			} else if(op.equals("front")) {
				System.out.println(dq.getFront());
			} else if(op.equals("back")) {
				System.out.println(dq.getBack());
			}
		}
	}
	
	public void run() throws IOException {
		input();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

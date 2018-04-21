package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.StringTokenizer;

class Stack {
	private class Node {
		private Object data;
		private Node next;
		public Node(Object data) {
			this.data = data;
			this.next = null;
		}
	}
	
	private Node top;
	private int size;
	
	public Stack() {
		this.top = null;
		this.size = 0;
	}
	
	public void push(Object data) {
		Node newNode = new Node(data);
		newNode.next = top;
		top = newNode;
		size++;
	}
	
	public Object pop() {
		if(isEmpty()) {
			return -1;
		}
		
		Object del = top.data;
		top = top.next;
		size--;
		return del;
	}
	
	public Object peek() {
		if(isEmpty()) {
			return -1;
		}
		
		return top.data;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public int getSize() {
		return size;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N;
		private Stack s = new Stack();
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				String op = st.nextToken();
				if(op.equals("push")) {
					int data = Integer.parseInt(st.nextToken());
					s.push(data);
				} else if(op.equals("top")) {
					System.out.println(s.peek());
				} else if(op.equals("pop")) {
					System.out.println(s.pop());
				} else if(op.equals("size")) {
					System.out.println(s.getSize());
				} else if(op.equals("empty")) {
					if(s.isEmpty()) {
						System.out.println("1");
					} else {
						System.out.println("0");
					}
				}
			}
		}
		
		public void run() throws IOException {
			input();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

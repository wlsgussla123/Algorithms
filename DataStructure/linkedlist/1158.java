package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class CircularLinkedList {
	class Node {
		int data;
		Node prev, next;
		public Node(int data) {
			this.data = data;
			this.prev = null;
			this.next = null;
		}
	}
	
	private Node head, tail, pointer;
	private int cnt = 0;
	
	public CircularLinkedList() {
		head = null;
		tail = null;
	}
	
	public void add(int data) {
		Node newNode = new Node(data);
		cnt++;
		
		if(head == null) {
			head = newNode;
			tail = newNode;
			pointer = head;
			return;
		} else {
			head.prev = newNode;
			newNode.prev = tail;
			newNode.next = head;
			tail.next = newNode;
			tail = newNode;
		}
	}
	
	public void remove(int data) {
		if(head == null) {
			return;
		}
		
		Node cur = head;
		while(cur != null) {
			if(cur.data == data) {
				if(cur.prev != null && cur.next != null) {
					if(cur == head) {
						head = cur.next;
						pointer = cur.next;
					} else if(cur == tail) {
						tail = cur.prev;
						pointer = cur.next;
					} else {
						pointer = cur.next;
					}
					cur.prev.next = cur.next;
					cur.next.prev = cur.prev;
				} else if(cur.prev == null && cur.next == null) {
					head = null;
					tail = null;
					pointer = null;
				} else if(cur.prev == null) {
					head = cur.next;
					tail = cur.next;
					pointer = cur.next;
				} else if(cur.next == null) {
					head = cur.prev;
					tail = cur.prev;
					pointer = cur.prev;
				}
				cnt--;
				break;
			}
			
			cur = cur.next;
		}
	}
	
	public void movePointer(int m) {
		for(int i=1; i<m; i++) {
			pointer = pointer.next;
		}
	}
	
	public Node getPointer() {
		return pointer;
	}
	
	public int getSize() {
		return cnt;
	}

	// for test
	public void print() {
		Node cur = head;
		for(int i=0; i<cnt; i++) {
			System.out.println(cur.data);
			cur = cur.next;
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().run();
	}

	static class Solution {
		private int N, M;
		private CircularLinkedList ll = new CircularLinkedList();
		private ArrayList<Integer> res = new ArrayList();
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}

		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
		}
		
		private void solution() {
			for(int i=1; i<=N; i++) {
				ll.add(i);
			}
			
			while(res.size() != N) {
				ll.movePointer(M);
				res.add(ll.getPointer().data);
				ll.remove(ll.getPointer().data);
			}
			
			System.out.print("<");
			int size = res.size();
			for(int i=0; i<size; i++) {
				if(i != size-1) {
					System.out.print(res.get(i) + ", ");
				} else {
					System.out.print(res.get(i));
				}
			}
			System.out.println(">");
		}

		public void run() throws IOException {
			input();
			solution();
			close();
		}

		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

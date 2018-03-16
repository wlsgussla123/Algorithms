package algo;

class Node {
	int data;
	Node prev;
	Node next;
	public Node(int data) {
		this.data = data;
		this.prev = null;
		this.next = null;
	}
}

class LinkedList {
	Node head;
	int size;
	
	public LinkedList() {
		this.head = null;
	}
	
	public void add(int data) {
		if(this.head == null) {
			this.head = new Node(data);
		} else {
			Node newNode = new Node(data);
			Node cur = head;
			while(cur.next != null) {
				cur = cur.next;
			}
			newNode.prev = cur;
			newNode.next = null;
			cur.next = newNode;
		}
		size++;
	}
	
	public void remove(int data) {
		Node cur = head;
		while(cur != null) {
			if(cur.data == data) {
				cur.next.prev = cur.prev;
				cur.prev.next = cur.next;
			}
			cur = cur.next;
		}
	}
	
	public int get(int index) {
		Node cur = head;
		int i=1;
		while(cur != null && i++<=index) {
			cur = cur.next;
		}
		return cur.data;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void print() {
		Node cur = head;
		while(cur!=null) {
			System.out.println(cur.data);
			cur = cur.next;
		}
	}
}

public class Main {
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.remove(3);
		list.print();
	}
}

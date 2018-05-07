package datastructure;

class LinkedList {
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
	
	private Node head = null, tail = null;
	private int size = 0;
	
	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	public void addAtHead(int data) {
		Node newNode = new Node(data);
		if(head == null) {
			head = newNode;
			tail = newNode;
			size++;
			return;
		}
		
		newNode.next = head;
		head.prev = newNode;
		head = newNode;
		size++;
	}
	
	public void addAtTail(int data) {
		Node newNode = new Node(data);
		if(tail == null) {
			head = newNode;
			tail = newNode;
			size++;
			return;
		}
		
		newNode.prev = tail;
		tail.next = newNode;
		tail = newNode;
		size++;
	}
	
	public void addAtIndex(int index, int data) {
		if(index < 0 || index > size) {
			System.out.println("index range is error");
			return;
		}
		
		Node cur = head;
		for(int i=0; i<index; i++) {
			cur = cur.next;
		}
		
		if(index == 0) {
			addAtHead(data);
			return;
		} else if(index == getSize()) {
			addAtTail(data);
			return;
		}
		
		Node newNode = new Node(data);
		if(cur.prev != null) {
			cur.prev.next = newNode;
			newNode.prev = cur.prev;
		} else if(cur.next != null) {
			cur.next.prev = newNode;
			newNode.next = cur.next;
		}
		size++;
	}
	
	public int removeAtHead() {
		if(isEmpty()) {
			System.out.println("list is empty");
			return -1;
		}
		int del = head.data;
		head = head.next;
		if(head == null) {
			tail = null;
		} else {
			head.prev = null;
		}
		
		size--;
		return del;
	}
	
	public int removeAtIndex(int index) {
		if(isEmpty()) {
			System.out.println("list is empty");
			return -1;
		}
		
		if(index < 0 || index > size-1) {
			System.out.println("index range is error");
			return -1;
		}
		
		Node cur = head;
		int del = -1;
		if(index == 0) {
			return removeAtHead();
		}
		
		if(index == getSize()-1) {
			return removeAtTail();
		}
		
		for(int i=0; i<index; i++) {
			cur = cur.next;
		}
		del = cur.data;
		
		if(cur.prev != null) {
			cur.prev.next = cur.next;
		} else if(cur.next != null) {
			cur.next.prev = cur.prev;
		}
		size--;
		return del;
	}
	
	public int removeAtTail() {
		if(isEmpty()) {
			System.out.println("list is empty");
			return -1;
		}
		int del = tail.data;
		tail = tail.prev;
		if(tail == null) {
			head = null;
		} else {
			tail.next = null;
		}
		size--;
		
		return del;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void printList() {
		Node cur = head;
		while(cur != null) {
			System.out.print(cur.data + " ");
			cur = cur.next;
		}
		System.out.println();
	}
}

public class LinkedListMain {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.addAtHead(4);
		ll.addAtHead(3);
		ll.addAtHead(2);
		ll.addAtHead(1);
		ll.printList();
		ll.addAtTail(5);
		ll.addAtTail(6);
		ll.printList();
		ll.addAtIndex(0, 0);
		ll.printList();
		ll.addAtIndex(7,7);
		ll.printList();
		ll.addAtTail(8);
		ll.printList();
		ll.removeAtHead();
		ll.printList();
		ll.removeAtTail();
		ll.printList();
		ll.removeAtIndex(1);
		ll.printList();
		ll.removeAtIndex(5);
		ll.removeAtIndex(0);
		ll.printList();
	}
}

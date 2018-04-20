package datastructure;

class LinkedList {
	private class Node {
		private Node prev;
		private Node next;
		private Object data;
		
		public Node(Object data) {
			this.data = data;
			this.prev = null;
			this.next = null;
		}
	}
	
	private Node head;
	private Node tail;
	private int size;
	
	public LinkedList() {
		this.head = null;
		this.tail = null;
		size = 0;
	}
	
	public void addAtTail(Object data) {
		if(isEmpty()) {
			head = new Node(data);
			tail = head;
			return;
		}
		
		Node newNode = new Node(data);
		newNode.prev = tail;
		tail.next = newNode;
		tail = newNode;
		size++;
	}
	
	public void addAtIndex(int index, Object data) {
		if(index < 0 || getSize() < index) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) {
			addAtHead(data);
			return;
		}
		if(index == getSize()) {
			addAtTail(data);
			return;
		}
		
		Node cur = head;
		int i=0;
		while(i<index) {
			cur = cur.next;
			i++;
		}
		
		Node newNode = new Node(data);
		cur.prev.next = newNode;
		newNode.prev = cur.prev;
		newNode.next = cur;
		cur.prev = newNode;
		size++;
	}
	
	public void addAtHead(Object data) {
		if(isEmpty()) {
			head = new Node(data);
			tail = head;
			return;
		}
		
		Node newNode = new Node(data);
		newNode.next = head;
		head.prev = newNode;
		head = newNode;
		size++;
	}
	
	public Object deleteAtHead() {
		if(isEmpty()) {
			return null;
		}
		
		Object del = head;
		size--;
		
		if(head == tail) {
			head = null;
			tail = null;
			return del;
		}
		head.next.prev = null;
		head = head.next;
		
		return del;
	}
	
	public Object deleteAtTail() {
		if(isEmpty()) {
			return null;
		}
		
		Object del = tail;
		size--;
		
		if(head == tail) {
			head = null;
			tail = null;
			return del;
		}
		tail.prev.next = null;
		tail = tail.prev;
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
			System.out.println(cur.data);
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
		ll.addAtTail(5);
		ll.addAtIndex(2, 10);
		ll.addAtIndex(0, 0);
		ll.addAtIndex(ll.getSize(), 100);
		ll.printList();
		ll.deleteAtTail();
		ll.deleteAtHead();
		ll.printList();
	}
}

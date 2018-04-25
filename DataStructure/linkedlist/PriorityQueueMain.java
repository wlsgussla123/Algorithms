package datastructure;

class Node {
	public int data;
	public int priority;
	public Node next;
	public Node(int data, int p) {
		this.data = data;
		this.priority = p;
		this.next = null;
	}
}

class PriorityQueueList {
	private Node head;
	
	public PriorityQueueList() {
		this.head = null;
	}
	
	public void push(int data, int priority) {
		Node newNode = new Node(data, priority);
		if(isEmpty()) {
			head = newNode;
			return;
		}
		
		if(head.priority > priority) {
			newNode.next = head;
			head = newNode;
		} else {
			Node cur = head;
			Node prev = head;
			while(cur != null) {
//				System.out.println(cur.data);
				if(cur.priority < priority) {
					prev = cur;
					cur = cur.next;
				} else {
					break;
				}
			}
			prev.next = newNode;
			newNode.next = cur;
		}
	}
	
	public Integer pop() {
		if(isEmpty()) {
			System.out.println("queue is empty");
			return null;
		}
		
		int del = head.data;
		head = head.next;
		return del;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
}

public class PriorityQueueMain {
	public static void main(String[] args) {
		PriorityQueueList pq = new PriorityQueueList();
		pq.push(2, 1);
		pq.push(3, 2);
		pq.push(4, 3);
		pq.push(1, 0);
		
		while(!pq.isEmpty()) {
			System.out.println(pq.pop());
		}
	}
}

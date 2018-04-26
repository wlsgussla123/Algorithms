package coding_interview;

class LinkedList {
	private class Node {
		int data;
		Node next;
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}
	
	private Node head;
	public LinkedList() {
		this.head = null;
	}
	
	private void addLast(int data) {
		Node newNode = new Node(data);
		if(isEmpty()) {
			head = newNode;
			return;
		}
		
		Node cur = head;
		while(cur.next != null) {
			cur = cur.next;
		}
		cur.next = newNode;
	}
	
	public void add(int data) {
		addLast(data);
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
	}
	
	public void removeOverlap() {
		Node cur = head;
		Node prev = null;
		Node next = head;
		
		while(cur != null) {
			prev = next;
			next = cur.next;
			while(next != null) {
				if(cur.data == next.data) {
					prev.next = next.next;
				}
				prev = next;
				next = next.next;
			}
			cur = cur.next;
		}
	}
}

public class CDCI_2_1 {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(2);
		ll.add(1);
		ll.add(3);
		ll.add(2);
		ll.printList();
		ll.removeOverlap();
		System.out.println();
		ll.printList();
	}
}


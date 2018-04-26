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
	
	private Node head, tail;
	public LinkedList() {
		this.head = null;
		this.tail = null;
	}
	
	private void addLast(int data) {
		Node newNode = new Node(data);
		if(isEmpty()) {
			head = newNode;
			tail = newNode;
			return;
		}
		
		tail.next = newNode;
		tail = newNode;
	}
	
	public void add(int data) {
		addLast(data);
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public Integer getData(int k) {
		if(isEmpty()) {
			System.out.println("list is empty");
			return null;
		}
		
		Node cur = head;
		Node runner = null;
		while(cur != null) {
			runner = cur;
			for(int i=0; i<k; i++) {
				runner = runner.next;
				if(runner == null) {
					return null;
				}
			}
			if(runner.data == tail.data) {
				return cur.data;
			}
			cur = cur.next;
		}
		
		return null; // 범위초과
	}
	
	public void printList() {
		Node cur = head;
		while(cur != null) {
			System.out.println(cur.data);
			cur = cur.next;
		}
	}
}

public class CDCI_2_2 {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(4);
		ll.add(5);
		Integer num = ll.getData(3);
		if(num != null) {
			System.out.println(num);
		} else {
			System.out.println("k값이 범위 초과");
		}
	}
}

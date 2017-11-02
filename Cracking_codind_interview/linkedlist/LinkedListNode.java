package algo;

public class LinkedListNode<T> {
	public ListNode<T> head;
	
	public LinkedListNode() {
		this.head = null;
	}
	
	void insertLastNode(T data) {
		ListNode<T> newNode = new ListNode(data);
		if(head == null) {
			head = newNode;
			return;
		}
		
		ListNode cur = head;
		while(cur.next != null) {
			cur = cur.next;
		}
		cur.next = newNode;
	}
	
	void insertMiddleNode(int index, T data) {
		ListNode<T> newNode = new ListNode(data);
		
		ListNode cur = head;
		int curIndex = 1;
		while(curIndex < index) {
			cur = cur.next;
			curIndex++;
		}
		
		newNode.next = cur.next;
		cur.next = newNode;
	}
	
	void insertFirstNode(T data) {
		ListNode<T> newNode = new ListNode(data);

		newNode.next = head;
		head = newNode;
	}
	
	T deleteFirstNode() {
		T delData = null;
		
		if(head == null) {
			System.out.println("list is empty");
		} else {
			delData = head.data;
			head = head.next;
		}
	
		return delData;
	}
	
	T deleteMiddleNode(String data) {
		T delData = null;
		ListNode<T> cur = null;
		ListNode<T> prev = null;
		
		if(head == null) {
			System.out.println("list is empty");
		} else {
			cur = head;
			while(!cur.data.equals(data)) {
				prev = cur;
				cur = cur.next;
			}
			
			delData = cur.data;
			prev.next = cur.next;
		}
		
		return delData;
	}
	
	T deleteLastNode() {
		T delData = null;
		ListNode<T> prev = null;
		ListNode<T> cur = null;
		
		if(head == null) {
			System.out.println("null pointer exception");
		} else {
			prev = head;
			cur = head.next;
			while(cur.next != null) {
				prev = cur;
				cur = cur.next;
			}
			delData = cur.data;
			prev.next = null;			
		}
		
		return delData;
	}
	
	void printList() {
		ListNode cur = head;
		
		while(cur != null) {
			System.out.println(cur.data);
			cur = cur.next;
		}
	}
}

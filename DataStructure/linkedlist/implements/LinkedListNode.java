package algo;

public class LinkedListNode {
	private ListNode head;
	
	public LinkedListNode() {
		this.head = null;
	}
	
	void insertLastNode(String data) {
		ListNode newNode = new ListNode(data);
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
	
	void insertMiddleNode(int index, String data) {
		ListNode newNode = new ListNode(data);
		
		ListNode cur = head;
		int curIndex = 1;
		while(curIndex < index) {
			cur = cur.next;
			curIndex++;
		}
		
		newNode.next = cur.next;
		cur.next = newNode;
	}
	
	void insertFirstNode(String data) {
		ListNode newNode = new ListNode(data);

		newNode.next = head;
		head = newNode;
	}
	
	String deleteFirstNode() {
		String delData = null;
		
		if(head == null) {
			System.out.println("list is empty");
		} else {
			delData = head.data;
			head = head.next;
		}
	
		return delData;
	}
	
	String deleteMiddleNode(String data) {
		String delData = null;
		ListNode cur = null;
		ListNode prev = null;
		
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
	
	String deleteLastNode() {
		String delData = null;
		ListNode prev = null;
		ListNode cur = null;
		
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

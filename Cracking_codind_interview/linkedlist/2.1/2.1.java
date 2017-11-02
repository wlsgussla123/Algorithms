package algo;

import java.util.HashSet;

public class main {
	public static void deleteDups(LinkedListNode list) {
		HashSet<String> set = new HashSet<String>();
		ListNode prev = null;
		
		ListNode cur = list.head;
		while(cur != null) {
			if(set.contains(cur.data)) {
				prev.next = cur.next;
			} else {
				set.add(cur.data);
				prev = cur;
			}
			cur = cur.next;
		}
	}
	
	public static void main(String args[]) {
		LinkedListNode head = new LinkedListNode();
		head.insertLastNode("1");
		head.insertLastNode("2");
		head.insertLastNode("3");
		head.insertFirstNode("1");
		head.insertLastNode("4");
		head.insertLastNode("1");
				
		head.printList();
		deleteDups(head);
		System.out.println("DD");
		head.printList();
		
	}
}

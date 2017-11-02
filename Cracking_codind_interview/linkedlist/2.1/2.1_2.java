package algo;

public class main {
	public static void deleteDups(LinkedListNode list) {
		ListNode cur = null;
		ListNode runner = null;
		
		if(list.head == null) {
			System.out.println("list is empty");
			return;
		} else {
			cur = list.head;
			while (cur != null) {
				runner = cur;
				while(runner.next != null) {
					if(runner.next.data.equals(cur.data)) {
						runner.next = runner.next.next;
					} else {
						runner = runner.next;
					}
				}
				cur = cur.next;
			}
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

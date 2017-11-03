package algo;

public class main {	
	public static LinkedListNode<Integer> add(LinkedListNode<Integer> list1, LinkedListNode<Integer> list2) {
		LinkedListNode<Integer> result = new LinkedListNode<>();

		if(list1.head == null || list2.head == null) {
			System.out.println("list empty");
			return null;
		}
		ListNode<Integer> cur1 = list1.head;
		ListNode<Integer> cur2 = list2.head;
		
	
		int num = 0;
		boolean checkRound = false;
		while(cur1 != null && cur2 != null) {
			if(checkRound)
				num = cur1.data + cur2.data + 1;
			else
				num = cur1.data + cur2.data;
			
			if(num >= 10) {
				num = num % 10;
				checkRound = true;
			}
			
			result.insertLastNode(num);
			
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		
		return result;
	} 
	
	public static void reverse(LinkedListNode<Integer> list) {
		ListNode<Integer> before = null;
		ListNode<Integer> cur = list.head;
		
		while(cur != null) {
			ListNode<Integer> next = cur.next;
			cur.next = before;
			before = cur;
			cur = next;
		}
		
		list.head = before;
	}
	
	public static void main(String args[]) {
		LinkedListNode<Integer> head1 = new LinkedListNode();
		LinkedListNode<Integer> head2 = new LinkedListNode();
		LinkedListNode<Integer> result = new LinkedListNode();
		
		head1.insertLastNode(7);
		head1.insertLastNode(1);
		head1.insertLastNode(6);
		head2.insertLastNode(5);
		head2.insertLastNode(9);
		head2.insertLastNode(2);
		
		result = add(head1, head2);
		reverse(result);
		result.printList();
	}
}

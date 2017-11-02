package algo;

public class main {
	public static void divide(LinkedListNode<Integer> list, int x) {
		ListNode<Integer> cur = null;
		ListNode<Integer> post = null;
		
		if(list.head == null) {
			System.out.println("list empty");
			return;
		} else {
			cur = list.head;
			post = cur.next;
			
			while(cur.next != null) {
				if(cur.next != null) {
					post = cur.next;					
				}
			
				if(post.data < x) {
					cur.next = post.next;					
					post.next = list.head.next;
					list.head.next = post;
				} else {
					cur = cur.next;	
				}				
			}
		}
	}
	
	public static void main(String args[]) {
		LinkedListNode<Integer> head = new LinkedListNode();
		head.insertLastNode(3);
		head.insertLastNode(5);
		head.insertLastNode(8);
		head.insertLastNode(5);
		head.insertLastNode(10);
		head.insertLastNode(2);
		head.insertLastNode(1);
		
		divide(head, 5);
			
		head.printList();
	}
}

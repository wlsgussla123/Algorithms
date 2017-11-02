package algo;

public class main {
	public static String pickFromReverse(LinkedListNode list, int k) {
		ListNode checkNode = null;
		String result = null;
		int len = 0;
	
		if(list.head == null) {
			System.out.println("list empty");
		} else {
			checkNode = list.head;
			while(checkNode.next != null) {
				checkNode = checkNode.next;
				len++;
			}
				
			ListNode temp = list.head;
			for(int i=0; i<len - k; i++) {
				temp = temp.next;
			}
			result = temp.data;
		}
		
		return result;
	}
	
	public static void main(String args[]) {
		LinkedListNode head = new LinkedListNode();
		head.insertLastNode("1");
		head.insertLastNode("2");
		head.insertLastNode("3");
		head.insertLastNode("4");
		head.insertLastNode("5");
		head.insertLastNode("6");
		
		String result = pickFromReverse(head, 5);
		System.out.println(result);
	}
}

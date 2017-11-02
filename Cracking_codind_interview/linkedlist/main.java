package algo;

import java.util.HashSet;

public class main {
	public static void main(String args[]) {
		LinkedListNode head = new LinkedListNode();
		head.insertLastNode("1");
		head.insertLastNode("2");
		head.insertLastNode("3");
		head.insertFirstNode("1");
		head.insertLastNode("4");
		head.insertLastNode("1");
				
		head.printList();
		System.out.println("DD");
		head.printList();
		
	}
}

package algo;

public class main {
	public static void main(String args[]) {
		LinkedListNode head = new LinkedListNode();
		head.insertLastNode("1");
		head.insertLastNode("2");
		head.insertLastNode("4");
		head.insertFirstNode("0");
		head.insertMiddleNode(3, "3");
		
		head.deleteFirstNode();
		head.deleteLastNode();
		head.deleteMiddleNode("3");
		
		head.printList();
	}
}

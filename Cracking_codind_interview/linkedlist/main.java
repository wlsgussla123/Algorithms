package algo;

public class main {
	public static void main(String args[]) {
		LinkedListNode<Integer> head = new LinkedListNode();
		head.insertLastNode(3);
		head.insertLastNode(5);
		head.insertLastNode(8);
		head.insertLastNode(5);
		head.insertLastNode(10);
		head.insertLastNode(2);
		head.insertLastNode(1);
			
		head.printList();
	}
}

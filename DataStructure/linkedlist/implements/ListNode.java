package algo;

public class ListNode {
	String data;
	ListNode next;
	
	public ListNode() {
		this.data = null;
		this.next = null;
	}
	
	public ListNode(String data) {
		this.data = data;
		this.next = null;
	}
	
	public String getData() {
		return this.data;
	}
}

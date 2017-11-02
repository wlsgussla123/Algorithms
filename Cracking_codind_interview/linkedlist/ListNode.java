package algo;

public class ListNode<T> {
	T data;
	ListNode<T> next;

	public ListNode(T data) {
		this.data = data;
		this.next = null;
	}
	
	public T getData() {
		return this.data;
	}
}

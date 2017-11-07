/*
 * 작성자 : 박진현
 * Queue 구현
 */

class MyQueue {
	private static class QueueNode {
		private int data;
		private QueueNode next;
		
		public QueueNode() {
		}
	}
	
	private QueueNode front = null;
	private QueueNode rare = null;
	
	public void add(int data) {
		QueueNode newNode = new QueueNode();
		newNode.data = data;
		newNode.next = null;
		
		if(isEmpty()) {
			front = newNode;
			rare = newNode;
		} else {
			rare.next = newNode;
			rare = newNode;
		}
	}
	
	public int remove() throws Exception {
		if(isEmpty()) throw new Exception(); 
		
		QueueNode temp = front;
		front = front.next;
		
		return temp.data;
	}
	
	public int peek() throws Exception {
		if(isEmpty()) throw new Exception();
		
		return front.data;
	}
	
	public boolean isEmpty() {
		return front == null ? true : false;
	}
}

public class Main {
	public static void main(String[] args) {
		MyQueue queue = new MyQueue();
		
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		
		try {
			System.out.println(queue.remove());
			System.out.println(queue.remove());
			System.out.println(queue.remove());
			System.out.println(queue.remove());
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("remove error");
		}
	}
}

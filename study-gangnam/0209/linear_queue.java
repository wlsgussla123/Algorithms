package algo;

class Queue {
	private int front;
	private int rear;
	private int qSize;
	private int[] data;
	
	public Queue(int qSize) {
		front = -1;
		rear = -1;
		this.qSize = qSize;
		data = new int[qSize];
	}
	
	public boolean isEmpty() {
		return front == rear;
	}
	
	public boolean isFull() {
		return rear == qSize-1;
	}
	
	public void enQueue(int d) {
		if(isFull()) System.out.println("Queue is Full");
		else data[++rear] = d; // rear == -1 이므로
	}
	
	// 비었으면 -1 반환
	public int deQueue() {
		return isEmpty() == true ? -1 : data[++front]; 
	}
	
	public int peek() {
		return isEmpty() == true ? -1 : data[front+1]; // front 값은 변하지 않게 
	}
	
	public void print() {
		for(int i=front+1; i<=rear; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
}

public class Main {
	public static void main(String[] args) {
		Queue q = new Queue(4);
		q.enQueue(1);
		q.enQueue(2);
		q.enQueue(3);
		q.enQueue(4);
		q.print();
		q.deQueue();
		q.print();
	}
}

package algo;

class CircleQueue {
	private int front;
	private int rear;
	private int qSize;
	private int[] data;
	
	public CircleQueue(int qSize) {
		front = 0;
		rear = 0;
		this.qSize = qSize;
		data = new int[qSize];
	}
	
	public boolean isEmpty() {
		return front == rear;
	}
	
	public boolean isFull() {
		return (rear+1)%qSize == front;
	}
	
	public void enQueue(int d) {
		if(isFull()) System.out.println("Queue is full");
		else {
			rear = (rear+1) % qSize;
			data[rear] = d;
		}
	}
	
	public int deQueue() {
		if(isEmpty()) {
			System.out.println("Queue is Empty");
			return -1;
		}
		else {
			front = (front+1) % qSize;
			return data[front];
		}
	}
	
	public int peek() {
		if(isEmpty()) {
			System.out.println("Queue is Empty");
			return -1;
		} else {
			return data[(front+1) % qSize];
		}
	}
	
	public void print() {
		if(isEmpty()) System.out.println("Queue is Empty");
		else {
			int i = front;
			while(i!=rear) {
				i = (i+1)%qSize;
				System.out.print(data[i] + " ");
			}
			System.out.println();
		}
	}
}

public class Main {
	public static void main(String[] args) {
		CircleQueue cq = new CircleQueue(4);
		cq.enQueue(1);
		cq.enQueue(2);
		cq.deQueue();
		cq.deQueue();
		cq.enQueue(1);
		cq.enQueue(2);
		cq.enQueue(3);
		cq.print();
	}
}

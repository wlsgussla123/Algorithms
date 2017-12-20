package algo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Task {
	private int N;
	private Queue<String>[] queueList;
	
	private final int MAX_AGE = 201;
	private final Scanner sc = new Scanner(System.in);
	
	@SuppressWarnings("unchecked")
	private void init() {
		queueList = new LinkedList[MAX_AGE];
		for(int i=0; i<MAX_AGE; i++) {
			queueList[i] = new LinkedList<>();
		}
	}
	
	private void input() {
		N = sc.nextInt();
		
		int age;
		String name;
		for(int i=0; i<N; i++) {
			age = sc.nextInt();
			name = sc.next();
			
			queueList[age].add(name);
		}
	}
	
	private void print() {
		for(int i=1; i<MAX_AGE; i++) {
			while(!queueList[i].isEmpty()) {
				System.out.println(i + " " + queueList[i].peek());
				queueList[i].remove();
			}
		}
	}
	
	public void run() {
		init();
		input();
		print();
	}
}

public class Main {
	public static void main(String[] args) {
		Task task = new Task();
		task.run();
	}
}

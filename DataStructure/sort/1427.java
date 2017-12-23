package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Task {
	private int N;
	private ArrayList<Integer> numbers;
	
	private final Scanner sc = new Scanner(System.in);
	
	private void init() {
		numbers = new ArrayList<Integer>();
	}
	
	private void input() {
		N = sc.nextInt();
		
		while(N > 0) {
			numbers.add(N % 10);
			N /= 10;
		}
		Collections.reverse(numbers);
	}
	
	private void sort() {
		Collections.sort(numbers, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1 > o2) {
					return -1;
				} else if(o1 == o2) {
					return 0;
				} else {
					return 1;
				}
			}
		});
	}

	private void print() {
		for(Integer num : numbers) {
			System.out.print(num);
		}
	}
	
	public void run() {
		init();
		input();
		sort();
		print();
	}
}

public class Main {
	public static void main(String[] args) {
		Task task = new Task();
		task.run();
	}
}

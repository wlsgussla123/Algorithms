package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private final int N = 5;
	int[] numbers = new int[N];
	
	public void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	public void bubbleSort() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N-1; j++) {
				if(numbers[j] > numbers[j+1]) {
					int temp = numbers[j];
					numbers[j] = numbers[j+1];
					numbers[j+1] = temp;
					print();
				}
			}
		}
	}
	
	public void print() {
		for(int i=0; i<N; i++) {
			System.out.print(numbers[i] + " ");
		}
		System.out.println("");
	}
	
	public void run() throws IOException {
		input();
		bubbleSort();
	}
}

public class main {		
	public static void main(String args[]) throws IOException {
		Task task = new Task();
		task.run();
	}
}

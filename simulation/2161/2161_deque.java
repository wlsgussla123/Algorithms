package algo;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;

class Task {
	private int N;
	private Deque<Integer> deque = new LinkedBlockingDeque<Integer>();
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
	}

	private void process() {
		for(int i=1; i<=N; i++) {
			deque.add(i);
		}
		
		while(!deque.isEmpty()) {
			System.out.print(deque.pollFirst() + " ");
			
			if(!deque.isEmpty())
				deque.addLast(deque.pollFirst());
		}
	}
	
	public void run() throws IOException {
		input();
		process();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

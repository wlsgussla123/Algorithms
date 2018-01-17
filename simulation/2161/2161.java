package algo;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N;
	private Queue<Integer> q = new LinkedList<Integer>();
	private List<Integer> list = new LinkedList<Integer>();
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
	}
	
	// 1 2 3 4
	private void process() {
		for(int i=1; i<=N; i++) {
			list.add(i);
		}
		
		int idx = 1;
		while(idx < N) {
			q.add(list.get(0)); // 1
			list.remove(0); // 2 3 4 
			list.add(list.get(0)); // 2 3 4 2
			list.remove(0); // 3 4 2
			idx++;
		}
		
		while(!q.isEmpty()) {
			System.out.print(q.poll() + " ");
		}
		System.out.println(list.get(0));
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

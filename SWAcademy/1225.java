package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private final int N = 8;
	private Queue<Integer> q;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		q = new LinkedList<>();
	}
	
	private void input() throws IOException {
		init();
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			q.add(Integer.parseInt(st.nextToken()));
		}
	}
	
	private void solution(int idx) {
		int n = 1;
		while(!q.isEmpty()) {
			int value = q.poll() - n;
			if(value <= 0) {
				value = 0;
				q.add(value);
				break;
			} else {
				q.add(value);
			}
			n = n == 5 ? 1: n+1;
		}
		
		System.out.print("#"+idx+ " ");
		while(!q.isEmpty()) {
			System.out.print(q.poll() + " ");
		}
	}
	
	public void run() throws IOException {
		for(int i=0; i<10; i++) {
			st = getStringTokenizer();
			int idx = Integer.parseInt(st.nextToken());
			input();
			solution(idx);
			System.out.println();
		}
		close();
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

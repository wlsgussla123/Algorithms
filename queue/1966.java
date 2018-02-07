package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	private int index;
	private int value;
	public Node(int i, int v) {
		this.index = i;
		this.value = v;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}

class Task {
	private Queue<Node> q;
	private PriorityQueue<Integer> pq;
	
	private int N,M;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		q = new LinkedList<Node>();
		pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
	}
	
	// 입력의 depth는 매우 잘 나오는 편
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			int value = Integer.parseInt(st.nextToken());
			q.add(new Node(i,value));
			pq.add(value);
		}
	}
	
	private void process() throws IOException {
		int cnt = 0;
		while(!q.isEmpty()) {
			if(q.peek().getValue() != pq.peek()) {
				Node temp = q.poll();
				q.add(temp);
			} else {
				Node temp = q.poll();
				pq.poll();
				cnt++;
				if(temp.getIndex() == M) break;
			}
		}
		System.out.println(cnt);
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		while(T-->0) {
			input();
			process();
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

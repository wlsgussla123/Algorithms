package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Distance {
	int x;
	int y;
	int l;
	public Distance(int x, int y) {
		this.x = x;
		this.y = y;
		this.l = x+y;
	}
}

class Task {
	private int N;
	private List<Distance> list;
	private boolean[] visited;
	private Distance s,e;
	private boolean answer = false;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		list = new ArrayList<Distance>();
		visited = new boolean[N+1];
		answer = false;
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		st = getStringTokenizer();
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		s = new Distance(x, y);
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			list.add(new Distance(x, y));
		}
		st = getStringTokenizer();
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		e = new Distance(x, y);
	}
	
	private boolean check(Distance a, Distance b) {
		return (Math.abs(b.x - a.x) + Math.abs(b.y - a.y)) <= 1000;
	}
	
	private void bfs() {
		Queue<Distance> q = new LinkedList<Distance>();
		for(int i=0; i<list.size(); i++) {
			if(check(s, list.get(i))) {
				q.add(list.get(i));
				visited[i] = true;
			}
		}
		
		while(!q.isEmpty()) {
			Distance cur = q.poll();
			if(check(cur, e)) {
				answer = true;
				break;
			}
			
			for(int i=0; i<list.size(); i++) {
				if(visited[i]) continue;
				if(check(cur, list.get(i))) {
					q.add(list.get(i));
					visited[i] = true;
				}
			}
		}
	}
	
	private void process() throws IOException {
		Collections.sort(list, new Comparator<Distance>() {
			@Override
			public int compare(Distance o1, Distance o2) {
				return o1.l - o2.l;
			}
		});
		
		if(check(s,e)) {
			System.out.println("happy");
			return;
		}
		bfs();
		if(answer) System.out.println("happy");
		else System.out.println("sad");
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

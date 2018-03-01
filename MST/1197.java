package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Kruskal implements Comparable<Kruskal> {
	int from;
	int to;
	int cost;
	
	public Kruskal(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Kruskal o) {
		return this.cost - o.cost;
	}
}

class Task {
	private int V,E;
	private int[] uf; // union-find 배열
	private ArrayList<Kruskal> edge = new ArrayList<Kruskal>(); // 크루스칼은 간선에 대한 정보를 갖고있다.
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		uf = new int[V+1];
		for(int i=1; i<=V; i++) {
			uf[i] = -1; // 자기 자신이 루트이므로
		}
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=0; i<E; i++) {
			st = getStringTokenizer();
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edge.add(new Kruskal(from, to, cost));
		}
	}
	
	private int find(int a) {
		if(uf[a] < 0) return a;
		return uf[a] = find(uf[a]);
	}
	
	private boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b) return false;
		uf[b] = a;
		return true;
	}
	
	private void solution() {
		Collections.sort(edge);
		
		int answer = 0;
		for(int i=0; i<E; i++) {
			if(merge(edge.get(i).from, edge.get(i).to)) {
				answer += edge.get(i).cost;
			}
		}
		System.out.println(answer);
	}
	
	public void run() throws IOException {
		input();
		solution();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

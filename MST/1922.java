package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int from;
	int to;
	int cost;
	public Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.cost - o.cost;
	}
}

class Task {
	private int V,E;
	private int[] uf;
	private ArrayList<Edge> edge = new ArrayList<>();
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		uf = new int[V+1];
		for(int i=0; i<=V; i++) {
			uf[i] = -1;
		}
	}
	
	private int find(int n) {
		if(uf[n]<0) return n;
		return uf[n] = find(uf[n]);
	}
	
	private boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		uf[b] = a;
		return true;
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		V = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		E = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=E; i++) {
			st = getStringTokenizer();
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edge.add(new Edge(from, to, cost));
		}
	}
	
	private void solution() throws IOException {
		Collections.sort(edge);
		
		int cost = 0;
		for(int i=0; i<E; i++) {
			if(merge(edge.get(i).from, edge.get(i).to)) {
				cost += edge.get(i).cost;
			}
		}
		bw.write(String.valueOf(cost));
	}
	
	public void run() throws IOException {
		input();
		solution();
		close();
	}
	
	private void close() throws IOException {
		bw.close();
		br.close();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

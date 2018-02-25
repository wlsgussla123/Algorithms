package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node {
	int to;
	int cost;
	
	public Node(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}
}

class Task {
	private int N,M; // 정점과 간선
	private ArrayList<ArrayList<Node>> list = new ArrayList<ArrayList<Node>>();
	private final int INF = 987654321;
	private int[] dist;
	private boolean negativeCycle = false;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		for(int i=0; i<=N; i++) list.add(new ArrayList<Node>());
	}
	
	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=M; i++) {
			st = getStringTokenizer();
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(from).add(new Node(to, cost));
		}
	}
	
	// 출발 도시가 1번이므로,
	private void bellmanFord() {
		dist[1] = 0;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				for(Node node : list.get(j)) {
					int to = node.to;
					int cost = node.cost;
					
					if(dist[j] != INF && dist[to] > dist[j] + cost) {
						dist[to] = dist[j] + cost;
						if(i==N) negativeCycle = true;
					}
				}
			}
		}
	}
	
	private void solution() {
		if(negativeCycle) System.out.println("-1");
		else {
			for(int i=2; i<=N; i++) {
				if(dist[i] == INF) System.out.println("-1");
				else System.out.println(dist[i]);
			}
		}
	}
	
	public void run() throws IOException {
		input();
		bellmanFord();
		solution();
	}
	
	private void print() {
		for(int i=1; i<=N; i++) {
			for(Node node : list.get(i)) {
				System.out.println(node.to + " " + node.cost);
			}
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

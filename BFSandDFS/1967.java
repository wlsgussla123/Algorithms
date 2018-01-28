package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Node {
	public int num;
	public int cost;
	public Node(int num, int cost) {
		this.num = num;
		this.cost = cost;
	}
}

class Task {
	private int N;
	private LinkedList<Node>[] tree;
	private Node maxDistNode = null;
	private boolean[] visited; 
	private int maxDist = 0;
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		tree = new LinkedList[N+1];
		visited = new boolean[N+1];
		for(int i=1; i<=N; i++) tree[i] = new LinkedList<>();
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=1; i<N; i++) {
			st = getStringTokenizer();
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			tree[from].add(new Node(to, cost));
			tree[to].add(new Node(from, cost));
		}
	}
	
	private void dfs(Node curNode, int cost) {
		visited[curNode.num] = true;
		
		for(Node next : tree[curNode.num]) {
			if(!visited[next.num]) {
				visited[next.num] = true;
				dfs(next, cost + next.cost);
				visited[next.num] = false;
			}
		}
		if(cost > maxDist) {
			maxDistNode = curNode;
			maxDist = cost;
		}
		visited[curNode.num] = false;
	}
	
	private void process() {
		// 루트로 부터 최대거리 노드를 탐색
		for(Node root : tree[1]) {
			visited[1] = true;
			dfs(root, root.cost);
			visited[1] = false;
		}
		maxDist = 0;
		dfs(maxDistNode, 0);
		System.out.println(maxDist);
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

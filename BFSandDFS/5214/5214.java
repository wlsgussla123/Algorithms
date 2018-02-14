package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,K,M;
	private ArrayList<ArrayList<Integer>> list;
	private boolean[] visited;
	private int[] dist;
	private final int INF = 987654321;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		list = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<=N+M; i++) list.add(new ArrayList<Integer>());
		dist = new int[N+M+1];
		Arrays.fill(dist, INF);
		visited = new boolean[N+M+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=M; i++) {
			st = getStringTokenizer();
			int temp = N + i;
			for(int j=0; j<K; j++) {
				int node = Integer.parseInt(st.nextToken());
				list.get(temp).add(node);
				list.get(node).add(temp);
			}
		}
	}
	
	private void bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		visited[start] = true;
		dist[start] = 1;
		
		while(!q.isEmpty()) {
			int u = q.poll();
			if(u == N) break;
			
			for(int x : list.get(u)) {
				if(!visited[x] && dist[x] > dist[u] + 1) {
					q.add(x);
					visited[x] = true;
					dist[x] = dist[u]+1;
				}
			}
		}
		
		System.out.println(dist[N] >= INF ? -1 : (dist[N]+1)/2);
	}

	public void run() throws IOException {
		input();
		bfs(1);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

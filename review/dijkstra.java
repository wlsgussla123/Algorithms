package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Task {
	private int V,E,K;
	private final int INF = 987654321;
	private ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
	private int[] dist;
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		for(int i=0; i<=V; i++) {
			adj.add(new ArrayList<>());
		}
		dist = new int[V+1];
		for(int i=1; i<=V; i++) {
			dist[i] = INF;
		}
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		init();
		st = getStringTokenizer();
		K = Integer.parseInt(st.nextToken());
		for(int i=0; i<E; i++) {
			st = getStringTokenizer();
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int[] edge = {v,w};
			adj.get(u).add(edge);
		}
	}
	
	private void dijkstra(int k) {
		dist[k] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		int[] pos = {k,0};
		pq.add(pos);
		while(!pq.isEmpty()) {
			int[] c = pq.poll();
			int cur = c[0];
			for(int[] edge : adj.get(cur)) {
				int next = edge[0];
				int n_cost = edge[1];
				
				if(dist[next] > dist[cur] + n_cost) {
					dist[next] = dist[cur] + n_cost;
					int[] n_edge = {next, dist[next]};
					pq.add(n_edge);
				}
			}
		}
		
		for(int i=1; i<=V; i++) {
			if(dist[i] == INF) System.out.println("INF");
			else System.out.println(dist[i]);
		}
	}
	
	public void run() throws IOException {
		input();
		dijkstra(K);
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

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
	private int N,M;
	private ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
	private final int INF = 987654321;
	private int[] dist;
	private int start, dest;
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		for(int i=0; i<=N; i++) {
			adj.add(new ArrayList<>());
		}
		dist = new int[N+1];
		for(int i=0; i<=N; i++) {
			dist[i] = INF;
		}
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
		init();
		
		int u,v,w;
		for(int i=0; i<M; i++) {
			st = getStringTokenizer();
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			int[] edge = {v,w};
			adj.get(u).add(edge);
		}
		st = getStringTokenizer();
		start = Integer.parseInt(st.nextToken());
		dest = Integer.parseInt(st.nextToken());
	}
	
	private void dijkstra(int start) {
		dist[start] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		int[] pos = {start, 0};
		pq.add(pos);
		while(!pq.isEmpty()) {
			int[] c = pq.poll();
			int c_idx = c[0];
			int c_cost = c[1];
			
			for(int[] edge : adj.get(c_idx)) {
				int n_idx = edge[0];
				int n_cost = edge[1];
				if(dist[n_idx] > dist[c_idx] + n_cost) {
					dist[n_idx] = dist[c_idx] + n_cost;
					int[] n_edge = {n_idx, dist[n_idx]};
					pq.add(n_edge);
				}
			}
		}
		
		System.out.println(dist[dest]);
	}
	
	public void run() throws IOException {
		input();
		dijkstra(start);
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

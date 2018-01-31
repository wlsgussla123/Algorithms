package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Task {
	private int V,E;
	private int startV; 
	private List<int[]>[] graph;
	private int[] dist;
	private final int INF = 987654321;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		graph = new List[V+1];
		for(int i=1; i<=V; i++) {
			graph[i] = new ArrayList<int[]>();
		}
		dist = new int[V+1];
		for(int i=1; i<=V; i++) dist[i] = INF;
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		init();
		st = getStringTokenizer();
		startV = Integer.parseInt(st.nextToken());
		for(int i=1; i<=E; i++) {
			st = getStringTokenizer();
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			int[] pos = {to, cost};
			graph[from].add(pos);
		}
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();		
	}

	private void dijkstra(int start) {
		dist[start] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		int[] pos = {start,0};
		pq.add(pos);
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int ct = cur[0];
			int cc = cur[1];
			
			for(int[] next : graph[ct]) {
				int nt = next[0];
				int nc = next[1];
				
				if(dist[nt] > cc + nc) {
					dist[nt] = cc + nc;
					int[] nextEdge = {nt, cc+nc};
					pq.add(nextEdge);
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
		dijkstra(startV);
		close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

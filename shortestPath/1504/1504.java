package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Task {
	private int V, E;
	private int A,B; // 반드시 거쳐야 하는 경로
	private List<int[]>[] graph;
	private long[][] dist;
	private final int INF = 987654321;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	@SuppressWarnings("unchecked")
	private void init() {
		graph = new List[V+1];
		for(int i=0; i<=V; i++) {
			graph[i] = new ArrayList<int[]>();
		}
		dist = new long[3][V+1];
		for(int i=0; i<3; i++) {
			for(int j=1; j<=V; j++) {
				dist[i][j] = INF;
			}
		}
	}

	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		init();
		
		int from, to, cost;
		for(int i=1; i<=E; i++) {
			st = getStringTokenizer();
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			int[] edge = {to, cost};
			graph[from].add(edge);
			int[] rEdge = {from, cost}; // 방향성 없기 때문에 반대도
			graph[to].add(rEdge);
		}
		
		st = getStringTokenizer();
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
	}
	
	private void dijkstra(int start, int idx) {
		PriorityQueue<int[]> headq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		dist[idx][start] = 0;
		int[] cur = {start, 0};
		headq.add(cur);
		int curIndex, curCost, nextIndex, nextCost;
		while(!headq.isEmpty()) {
			cur = headq.poll();
			curIndex = cur[0];
			curCost = cur[1];
			for(int[] edge: graph[curIndex]) {
				nextIndex = edge[0];
				nextCost = edge[1];
				
				if(dist[idx][nextIndex] > curCost + nextCost) {
					dist[idx][nextIndex] = curCost + nextCost;
					int[] nextEdge = {nextIndex, curCost + nextCost};
					headq.add(nextEdge);
				}
			}
		}
	}
	
	public void run() throws IOException {
		input();
		dijkstra(1, 0);
		dijkstra(A, 1);
		dijkstra(B, 2);
		long dist1 = dist[0][A] + dist[1][B] + dist[2][V];
		long dist2 = dist[0][B] + dist[2][A] + dist[1][V];
		long result = Math.min(dist1, dist2);
		if(result >= INF) {
			System.out.println("-1");
		} else {
			System.out.println(result);
		}
		
		br.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

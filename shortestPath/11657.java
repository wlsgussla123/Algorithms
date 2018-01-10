package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

import org.omg.CORBA.INTERNAL;

class Task {
	private int N,M;
	private int[] dist;
	private List<int[]>[] adj;
	private final int INF = 987654321;
	private boolean negativeCycle = false;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	@SuppressWarnings("unchecked")
	private void init() {
		dist = new int[N+1];
		for(int i=1; i<=N; i++) dist[i] = INF;
		adj = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			adj[i] = new ArrayList<int[]>();
		}
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
			int[] edge = {to, cost};
			adj[from].add(edge);
		}
	}
	
	private void bellmanFord() {
		dist[1] = 0; // 시작점은 0
		// 최단 경로이기 때문에, 같은 정점을 두 번 지날 일이 없다. (가능한 최단 경로의 간선 개수는 V-1개)
		// N까지 돌리는 이유는 마지막에 음의 싸이클 존재여부를 확인하기 위하여.
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				for(int[] edge : adj[j]) {
					int next = edge[0];
					int cost = edge[1];
					
					if(dist[j] != INF && dist[next] > dist[j] + cost) {
						dist[next] = dist[j] + cost;
						// N번째 루프에서 값이 갱신되면 음의 싸이클이 존재
						if(i == N) negativeCycle = true;
					}
				}
			}
		}
	}
	
	private void print() {
		if(negativeCycle) {
			System.out.println("-1");
		} else {
			for(int i=2; i<=N; i++) {
				System.out.println(dist[i] == INF ? "-1" : dist[i]);
			}
		}
	}

	public void run() throws IOException {
		input();
		bellmanFord();
		print();
		br.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

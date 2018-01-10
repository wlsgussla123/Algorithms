package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int V,E;
	private int A,B;
	private long[][] adj;
	private final int INF = 987654321;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		adj = new long[V+1][V+1];
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				adj[i][j] = INF;
			}
			adj[i][i] = 0;
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
			adj[from][to] = adj[to][from] = cost;
		}
		
		st = getStringTokenizer();
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
	}
	
	private void floydWarshall() {
		for(int k=1; k<=V; k++) {
			for(int i=1; i<=V; i++) {
				for(int j=1; j<=V; j++) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
	}
	
	private void getAnswer() {
		long dist1 = adj[1][A] + adj[A][B] + adj[B][V];
		long dist2 = adj[1][B] + adj[B][A] + adj[A][V];
		long result = Math.min(dist1, dist2);
		System.out.println(result >= INF ? "-1" : result);
	}
	
	public void run() throws IOException {
		input();
		floydWarshall();
		getAnswer();
		br.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

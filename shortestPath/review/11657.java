package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private List<int[]> graph[];
	private int[] dist;
	private final int INF = 987654321;
	private boolean isNegative = false;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<int[]>();
		}
		dist = new int[N+1];
		for(int i=1; i<=N; i++) dist[i] = INF;
	}
	
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
			int[] pos = {to,cost};
			graph[from].add(pos);
		}
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void bellmanford() {
		dist[1] = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				for(int[] next : graph[j]) {
					int nt = next[0];
					int nc = next[1];
					
					if(dist[j] != INF && dist[nt] > dist[j] + nc) {
						dist[nt] = dist[j] + nc;
						if(i==N) isNegative = true;
					}
				}
			}
		}
		
		if(isNegative) System.out.println("-1");
		else {
			for(int i=2; i<=N; i++) System.out.println(dist[i]!=INF ? dist[i] : "-1");
		}
	}

    public void run() throws IOException {
		input();
		bellmanford();
		close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

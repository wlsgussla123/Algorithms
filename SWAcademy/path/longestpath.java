package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[][] adj;
	private int answer;
	private boolean[] visited;
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		visited = new boolean[N+1];
		adj = new int[N+1][N+1];
		answer = -1;
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
			adj[from][to] = 1;
			adj[to][from] = 1;
		}
	}
	
	private void solution(int idx, int cnt) {
		if(cnt > answer) answer = cnt;
		for(int i=1; i<=N; i++) {
			if(adj[idx][i] == 1 && !visited[i]) {
				visited[i] = true;
				solution(i, cnt+1);
				visited[i] = false;
			}
		}
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		int idx = 1;
		while(idx <= T) {
			input();
			for(int i=1; i<=N; i++) {
				visited[i] = true;
				solution(i, 1);
				visited[i] = false;
			}
			System.out.println("#"+idx + " "+answer);
			idx++;
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

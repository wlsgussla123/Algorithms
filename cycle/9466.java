package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[] graph;
	private boolean[] visited;
	private boolean[] finished;
	private int cnt = 0;
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		graph = new int[N+1];
		visited = new boolean[N+1];
		finished = new boolean[N+1];
		cnt = 0;
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		st = getStringTokenizer();
		for(int i=1; i<=N; i++) {
			graph[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private void dfs(int index) {
		visited[index] = true;
		int next = graph[index];
		if(visited[next]) {
			if(!finished[next]) {
				for(int temp=next; temp!=index; temp=graph[temp]) cnt++;
				cnt++;
			}
		} else {
			dfs(next);
		}
		finished[index] = true;
	}
	
	private void process() {
		for(int i=1; i<=N; i++) {
			if(!visited[i]) dfs(i);
		}
		System.out.println(N-cnt);
	}
	

	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		while(T-->0) {
			input();
			process();			
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

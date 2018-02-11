package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private List<Integer>[] adj;
	private int[] staff;
	private int[] todo;
	private boolean[] visited;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		adj = new List[N+1];
		for(int i=1; i<=N; i++) adj[i] = new ArrayList<Integer>();
		staff = new int[N+1];
		todo = new int[M+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			int K = Integer.parseInt(st.nextToken());
			for(int j=1; j<=K; j++) {
				adj[i].add(Integer.parseInt(st.nextToken()));
			}
		}
	}
	
	private boolean dfs(int index) {
		if(visited[index]) return false;
		visited[index] = true;
		
		for(Integer num : adj[index]) {
			if(todo[num] == 0 || dfs(todo[num])) {
				staff[index] = num;
				todo[num] = index;
				return true;
			}
		}
		return false;
	}
	
	private void process() {
		int match = 0;
		for(int i=1; i<=N; i++) {
			if(staff[i] == 0) {
				visited = new boolean[N+1];
				if(dfs(i)) match++;
			}
		}
		System.out.println(match);
	}
	
	public void run() throws IOException {
		input();
		process();
	}
	
	private void print() {
		for(int i=1; i<=N; i++) {
			for(Integer num : adj[i]) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private ArrayList<ArrayList<Integer>> adj;
	private int[] staff;
	private int[] todo;
	private int[] visited;
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		adj = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<=N; i++) adj.add(new ArrayList<Integer>());
		staff = new int[N+1];
		todo = new int[M+1];
	}
	
	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			int k = Integer.parseInt(st.nextToken());
			for(int j=1; j<=k; j++) {
				adj.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
	}
	
	private boolean dfs(int index) {
		if(visited[index] >= 2) return false;
		visited[index]++;
		
		for(int num : adj.get(index)) {
			if(todo[num] == 0 || dfs(todo[num])) {
				staff[index] = num;
				todo[num] = index;
				return true;
			}
		}
		return false;
	}
	
	private void solution() {
		int match = 0;
		for(int i=1; i<=N; i++) {
			visited = new int[N+1];
			if(dfs(i)) match++;
			if(dfs(i)) match++;
		}
		System.out.println(match);
	}
	
	public void run() throws IOException {
		input();
		solution();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

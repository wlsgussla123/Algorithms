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
	private int[] cows;
	private int[] stable;
	private List<Integer>[] adj;
	private boolean[] visited;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		adj = new List[N+1];
		for(int i=1; i<=N; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		cows = new int[N+1];
		stable = new int[M+1];
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
				int S = Integer.parseInt(st.nextToken());
				adj[i].add(S);
			}
		}
	}
	
	private boolean dfs(int index) {
		if(visited[index]) return false;
		visited[index] = true;
		for(Integer num : adj[index]) {
			// 축사가 배정 안 되어있거나 매칭되었지만 원래 매칭된 것을 다른 것으로 바꿀 수 있다면
			if(stable[num] == 0 || dfs(stable[num])) {
				cows[index] = num;
				stable[num] = index;
				return true;
			}
		}
		
		return false;
	}
	
	private void process() {
		int match = 0;
		for(int i=1; i<=N; i++) {
			if(cows[i] == 0) {
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
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

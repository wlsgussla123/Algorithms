package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[] staff;
	private int[] todo;
	private int[][] adj;
	private boolean[] visited;
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		staff = new int[N+1];
		todo = new int[M+1];
		adj = new int[N+1][M+1];
		visited = new boolean[M+1];
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			int len = Integer.parseInt(st.nextToken());
			for(int j=1; j<=len; j++) {
				int todo = Integer.parseInt(st.nextToken());
				adj[i][todo] = 1;
			}
		}
	}
	
	private boolean dfs(int idx) {
		for(int i=1; i<=M; i++) {
			if(adj[idx][i] == 0) continue;
			if(visited[i]) continue;
			visited[i] = true;
			if(todo[i] == 0 || dfs(todo[i])) {
				todo[i] = idx;
				staff[idx] = i;
				return true;
			}
		}
		
		return false;
	}
	
	private void solution() throws IOException {
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			if(staff[i] != 0) continue;
			visited = new boolean[M+1];
			if(dfs(i)) cnt++;
		}
		bw.write(String.valueOf(cnt));
	}
	
	public void run() throws IOException {
		input();
		solution();
		close();
	}
	
	private void close() throws IOException {
		bw.close();
		br.close();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

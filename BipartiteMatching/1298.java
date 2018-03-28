package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[] student;
	private int[] laptop;
	private int[][] adj;
	private boolean[] visited;
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		student = new int[N+1];
		laptop = new int[5001];
		adj = new int[N+1][5001];
		visited = new boolean[5001];
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=M; i++) {
			st = getStringTokenizer();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a][b] = 1;
		}
	}
	
	private boolean dfs(int idx) {
		for(int i=1; i<=5000; i++) {
			if(visited[i]) continue;
			if(adj[idx][i] == 1) {
				visited[i] = true;
				if(laptop[i] == 0 || dfs(laptop[i])) {
					laptop[i] = idx;
					student[idx] = i;
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void solution() throws IOException {
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			visited = new boolean[5001];
			if(student[i] == 0) {
				if(dfs(i)) cnt++;
			}
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

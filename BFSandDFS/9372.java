package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[][] map;
	private boolean[] visited;
	private int answer;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
		visited = new boolean[N+1];
		answer = 987654321;
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<M; i++) {
			st = getStringTokenizer();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[b][a] = 1;
		}
	}
	
	private void bfs(int index) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(index);
		int cnt = 0;
		visited[index] = true;
		while(!q.isEmpty()) {
			int c = q.poll();
			visited[c] = true;
			
			for(int i=1; i<=N; i++) {
				if(map[c][i] != 0 && !visited[i]) {
					visited[i] = true;
					cnt++;
					q.add(i);
				}
			}
		}
		System.out.println(cnt);
	}
	
	private void process() {
		bfs(1);
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

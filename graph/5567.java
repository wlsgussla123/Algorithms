package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[][] map;
	private boolean[] visited;
	private int answer = 0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
		visited = new boolean[N+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=M; i++) {
			st = getStringTokenizer();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1;
			map[b][a] = 1;
		}
	}
	
	private void bfs(int index) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {index, 0};
		q.add(pos);
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int ci = cur[0];
			int cc = cur[1];
			if(cc == 2) continue;
			
			for(int i=2; i<=N; i++) {
				if(!visited[i] && map[ci][i] != 0) {
					visited[i] = true;
					answer++;
					int[] next = {i, cc+1};
					q.add(next);
				}
			}
		}
	}
	
	private void process() {
		bfs(1);
		System.out.println(answer);
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

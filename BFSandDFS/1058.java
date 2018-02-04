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
	private int N;
	private char[][] map;
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
		map = new char[N+1][N+1];
		visited = new boolean[N+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=N; j++) {
				map[i][j] = input[j-1];
				map[j][i] = input[j-1];
			}
		}
	}
	
	private void bfs(int index) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {index, 0};
		q.add(pos);
		int cnt = 0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cc = cur[1];
			if(cc==2) continue;
			
			for(int i=1; i<=N; i++) {
				if(index == i) continue;
				if(!visited[i] && map[cx][i]!='N') {
					visited[i] = true;
					int[]next = {i, cc+1};
					q.add(next);
					cnt++;
				}
			}
		}
		if(cnt > answer) answer = cnt;
	}
	
	private void clear() {
		for(int i=1; i<=N; i++) visited[i] = false;
	}

	private void process() {
		for(int i=1; i<=N; i++) {
			bfs(i);
			clear();
		}
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

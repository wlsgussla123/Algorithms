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
	private int F,S,G,U,D;
	private boolean[] visited;
	private int answer;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		visited = new boolean[F+1];
		answer = 987654321;
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		init();
	}
	
	private void bfs(int start) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {start, 0};
		q.add(pos);
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int index = cur[0];
			int cnt = cur[1];
			visited[index] = true;
			if(index == G) {
				answer = cnt;
				return;
			}
			
			if(index+U >= 1 && index+U <= F && !visited[index+U]) {
				int[] next = {index+U, cnt+1};
				visited[index+U] = true;
				q.add(next);
			}
			if(index-D >= 1 && index-D <= F && !visited[index-D]) {
				int[] next = {index-D, cnt+1};
				visited[index-D] = true;
				q.add(next);
			}
		}
	}

	private void process() throws IOException {
		bfs(S);
		if(answer == 987654321) System.out.println("use the stairs");
		else System.out.println(answer);
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

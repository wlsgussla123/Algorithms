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
	private int A,B,N,M;
	private int[] map = new int[100001];
	private boolean[] visited = new boolean[100001];
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	}

	void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void process() {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {N,0};
		int cnt = 0;
		q.add(pos);
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int n = cur[0];
			int c = cur[1];
			visited[n] = true;
			
			if(n == M) {
				cnt = c;
				break;
			}
			
			if(n+1>=0 && n+1<=100000 && !visited[n+1]) {
				int[] next = {n+1, c+1};
				visited[n+1] = true;
				q.add(next);
			}
			if(n-1>=0 && n-1<=100000 && !visited[n-1]) {
				int[] next = {n-1, c+1};
				visited[n-1] = true;
				q.add(next);
			}
			if(n+A>=0 && n+A<=100000 && !visited[n+A]) {
				int[] next = {n+A, c+1};
				visited[n+A] = true;
				q.add(next);
			}
			if(n-A>=0 && n-A<=100000 && !visited[n-A]) {
				int[] next = {n-A, c+1};
				visited[n-A] = true;
				q.add(next);
			}
			if(n+B>=0 && n+B<=100000 && !visited[n+B]) {
				int[] next = {n+B, c+1};
				visited[n+B] = true;
				q.add(next);
			}
			if(n-B>=0 && n-B<=100000 && !visited[n-B]) {
				int[] next = {n-B, c+1};
				visited[n-B] = true;
				q.add(next);
			}
			if(n*A>=0 && n*A<=100000 && !visited[n*A]) {
				int[] next = {n*A, c+1};
				visited[n*A] = true;
				q.add(next);
			}
			if(n*B>=0 && n*B<=100000 && !visited[n*B]) {
				int[] next = {n*B, c+1};
				visited[n*B] = true;
				q.add(next);
			}
		}
		
		System.out.println(cnt);
	}
	
	public void run() throws IOException {
		input();
		process();
		close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

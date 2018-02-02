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
	private int[] jump;
	private boolean[] visited;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		jump = new int[N];
		visited = new boolean[N];
		st = getStringTokenizer();
		for(int i=0; i<N; i++) jump[i] = Integer.parseInt(st.nextToken());
	}
	
	private void bfs(int index) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {index, 0};
		q.add(pos);
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int ci = cur[0];
			int cc = cur[1];
			visited[ci] = true;
			if(ci == N-1) {
				System.out.println(cc);
				return;
			}
			
			for(int i=1; i<=jump[ci]; i++) {
				int nx = ci+i;
				if(nx>=0 && nx<N && !visited[nx]) {
					visited[nx] = true;
					int[] next = {nx, cc+1};
					q.add(next);
				}
			}
		}
		System.out.println("-1");
	}

    public void run() throws IOException {
    	input();
    	bfs(0);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}


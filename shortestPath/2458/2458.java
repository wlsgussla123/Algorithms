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
	private int[] big;
	private int[] small;
	private boolean[] visited;
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		big = new int[N+1];
		small = new int[N+1];
		visited = new boolean[N+1];
		
		for(int i=1; i<=M; i++) {
			st = getStringTokenizer();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1; // a보다 큰학생들.
		}
	}
	
	private void process() {
		for(int i=1; i<=N; i++) {
			Queue<Integer> q = new LinkedList<Integer>();
			visited[i] = true;
			for(int j=1; j<=N; j++) {
				if(map[i][j] == 1) {
					q.add(j);
					visited[j] = true;
				}
			}
			
			while(!q.isEmpty()) {
				int there = q.poll(); // i보다 큰놈들
				small[there]++;
				big[i]++;
				
				for(int k=1; k<=N; k++) {
					if(map[there][k] == 1 && !visited[k]) {
						visited[k] = true;
						q.add(k);
					}
				}
			}
			
			for(int a=1; a<=N; a++) visited[a] = false;
		}
		
		int answer=0;
		for(int i=1; i<=N; i++) {
			if(small[i] + big[i] + 1 == N) answer++;
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

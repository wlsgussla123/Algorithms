package algo;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,A,B,M;
	private int[][] graph;
	private boolean[][] visited;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		graph = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		st = getStringTokenizer();
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
		int x,y;
		for(int i=1; i<=M; i++) {
			st = getStringTokenizer();
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			graph[x][y] = 1;
			graph[y][x] = 1;
		}
	}
	
	// BFS를 이용하여 촌수를 구해보자. (A와 B의 촌수)
	private void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		// 구하려는 A의 1촌 관계를 큐에 넣는다. 
		for(int i=1; i<=N; i++) {
			if(graph[A][i] != 0 && !visited[A][i]) {
				int[] pos = {A,i,1}; // 1촌 관계이므로 당연히 1 삽입
				visited[A][i] = true;
				q.add(pos);
			}
		}
		
		// 1촌 관계가 있으면 반복
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int c = cur[2];
			
			// n촌이 찾는 촌이라면 촌수 출력하고 종료
			if(y==B) {
				System.out.println(c);
				return;
			}
			
			// 1촌의 1촌을 또 찾는다. 이때는 촌수 +1
			for(int i=1; i<=N; i++) {
				if(graph[y][i] != 0 && !visited[y][i]) {
					int[] pos = {y,i,c+1};
					visited[y][i] = true;
					q.add(pos);
				}
			}
		}
		
		// 답이 안 구해지면 -1
		System.out.println("-1");
	}
	
	public void run() throws IOException {
		input();
		bfs();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

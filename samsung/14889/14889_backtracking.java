package algo;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[][] map;
	private boolean[] visited;
	private int answer = 1000000;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
		init();
		
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private void backtracking(int row, int cnt) {
		if(cnt == N/2) {
			int sumA = 0;
			int sumB = 0;
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(i==j) continue;
					if(visited[i] && visited[j]) {
						sumA += map[i][j];
					}
					
					if(!visited[i] && !visited[j]) {
						sumB += map[i][j];
					}
				}
			}
			
			int diff = Math.abs(sumA - sumB);
			if(answer > diff) answer = diff;
		} else {
			for(int i=row; i<=N; i++) {
				if(!visited[i]) {
					visited[i] = true;
					backtracking(i, cnt+1);
					visited[i] = false;
				}
			}
		}
	}
	
	public void run() throws IOException {
		input();
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				backtracking(i, 1);
				visited[i] = false;
			}
		}
		
		System.out.println(answer);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

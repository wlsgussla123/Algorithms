package algo;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	private boolean[] visited;
	private int[] arr;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		arr = new int[N+1];
		visited = new boolean[N+1];		
	}
	
	private void input() throws IOException {
		init();
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private void backtracking(int index, int len) {
		if(len == 6) {
			for(int i=1; i<=N; i++) {
				if(visited[i]) System.out.print(arr[i] + " ");
			}
			System.out.println();
		} else {
			for(int i=index+1; i<=N; i++) {
				if(!visited[i]) {
					visited[i] = true;
					backtracking(i, len+1);
					visited[i] = false;					
				}
			}
		}
	}
	
	public void run() throws IOException {
		while(true) {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			if(N==0) break;
			input();

			for(int i=1; i<=N; i++) {
				if(!visited[i]) {
					visited[i] = true;
					backtracking(i, 1);
					visited[i] = false;
				}
			}			
			System.out.println();
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

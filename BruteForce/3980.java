package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private final int N = 11;
	private int[][] map;
	private boolean[] visited;
	private int answer;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		map = new int[N][N];
		visited = new boolean[N];
		answer = 0;
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private void dfs(int idx, int pos, int sum) {
		if(idx == N-1) {
			answer = answer > sum ? answer : sum;
			return;
		}
			
		for(int i=0; i<N; i++) {
			if(visited[i] || map[idx+1][i] == 0) continue;
			visited[i] = true;
			dfs(idx+1 ,i, sum + map[idx+1][i]);
			visited[i] = false;
		}
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			input();
			for(int i=0; i<N; i++) {
				if(map[0][i] == 0) continue;
				visited[i] = true;
				dfs(0, i, map[0][i]);
				visited[i] = false;
			}
			System.out.println(answer);
		}
	}
	
	private void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

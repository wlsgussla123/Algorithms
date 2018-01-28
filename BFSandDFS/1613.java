package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,K;
	private int[][] map;
	private boolean[] visited;
	
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
		K = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=K; i++) {
			st = getStringTokenizer();
			int before = Integer.parseInt(st.nextToken());
			int after = Integer.parseInt(st.nextToken());
			map[before][after] = -1;
			map[after][before] = 1;
		}
	}
	
	private void dfs(int index, int origin, int value) {
		if(map[origin][index] == 0) {
			map[origin][index] = value;
			map[index][origin] = value * -1;
		}
		
		for(int i=1; i<=N; i++) {
			if(!visited[i] && map[index][i] == value) {
				visited[i] = true;
				dfs(i, origin, value);
			}
		}
	}
	
	private void clear() {
		for(int i=1; i<=N; i++) visited[i] = false;
	}
	
	private void process() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j] == -1 && !visited[j]) {
					visited[j] = true;
					dfs(j,i, map[i][j]);
				}
			}
			clear();
		}
	}
	
	private void infer() throws IOException {
		st = getStringTokenizer();
		int M = Integer.parseInt(st.nextToken());
		for(int i=0; i<M; i++) {
			st = getStringTokenizer();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bw.write(String.valueOf(map[a][b])+"\n");
		}
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();		
	}

	public void run() throws IOException {
		input();
		process();
		infer();
		close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

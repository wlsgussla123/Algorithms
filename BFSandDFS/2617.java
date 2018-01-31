package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[][] map;
	private boolean[][] visited;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=M; i++) {
			st = getStringTokenizer();
			int heavy = Integer.parseInt(st.nextToken());
			int light = Integer.parseInt(st.nextToken());
			map[heavy][light] = 2;
			map[light][heavy] = 1;
		}
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();		
	}

	private void clear() {
		for(int i=1; i<=N; i++) 
			for(int j=1; j<=N; j++)
				visited[i][j] = false;
	}
	
	private void dfs(int y, int origin, int value) {
		for(int i=1; i<=N; i++) {
			if(map[y][i] == value && !visited[y][i]) {
				visited[y][i] = true;
				map[origin][i] = value;
				dfs(i,origin,value);
			}
		}
	}
	
	private void count() {
		int answer = 0;
		int cnt1 = 0;
		int cnt2 = 0;
		for(int i=1; i<=N; i++) {
			cnt1 = 0;
			cnt2 = 0;
			for(int j=1; j<=N; j++) {
				if(map[i][j] == 1) cnt1++;
				if(map[i][j] == 2) cnt2++;
			}
			if(cnt1 > N/2) answer++;
			if(cnt2 > N/2) answer++;
		}
		System.out.println(answer);
	}

	private void process() throws IOException {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j] != 0) {
					visited[i][j] = true;
					dfs(j,i,map[i][j]);
					clear();	
				}
			}
		}
		count();
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

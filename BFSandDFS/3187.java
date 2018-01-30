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
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	private int answerK, answerV;
	private int k=0, v=0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=M; j++) {
				map[i][j] = input[j-1];
				if(input[j-1] == 'k') answerK++;
				else if(input[j-1] == 'v') answerV++;
			}
		}
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void dfs(int x, int y) {
		if(map[x][y] == 'k') k++;
		if(map[x][y] == 'v') v++;
				
		for(int i=0; i<4; i++) {
			int nx = x + dirs[i][0];
			int ny = y + dirs[i][1];
			
			if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny] && map[nx][ny] != '#') {
				visited[nx][ny] = true;
				dfs(nx,ny);
			}
		}
	}
	
	private void process() throws IOException {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				visited[i][j] = true;
				dfs(i,j);
				if(k>v) answerV -= v;
				else answerK -= k;
				k=0; v=0;
			}
		}
		System.out.println(answerK + " " + answerV);
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

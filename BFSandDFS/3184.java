package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private char[][] map;
	private boolean[][] visited;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	private int answerO, answerV;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[N+1][M+1];
		visited = new boolean[N+1][M+1];
		answerO = 0;
		answerV = 0;
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
				if(input[j-1] == 'o') answerO++;
				if(input[j-1] == 'v') answerV++;
			}
		}
	}
	
	private void bfs(int sx, int sy) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {sx,sy};
		visited[sx][sy] = true;
		q.add(pos);
		int oCnt=0, vCnt=0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			if(map[x][y] == 'o') oCnt++;
			if(map[x][y] == 'v') vCnt++;
			
			for(int i=0; i<4; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny]) {
					if(map[nx][ny] == '#') continue;
					int[] next = {nx,ny};
					visited[nx][ny] = true;
					q.add(next);
				}
			}
		}
		
		if(oCnt > vCnt) answerV -= vCnt;
		else answerO -= oCnt;
	}
	
	private void process() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(!visited[i][j] && map[i][j] != '#') {
					bfs(i,j);
				}
			}
		}
		
		System.out.println(answerO + " " + answerV);
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

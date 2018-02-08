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
	private int N,M;
	private char[][] map;
	private boolean[][] visited;
	private boolean[] key;
	private int answer;
	private int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[N+1][M+1];
		visited = new boolean[N+1][M+1];
		key = new boolean[26];
		answer = 0;
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
			}
		}
		st = getStringTokenizer();
		char[] keys = st.nextToken().toCharArray();
		for(char k : keys) {
			if(k >= 'a' && k <= 'z') key[k-'a'] = true;
		}
	}
	
	private boolean getKeys(int originX, int originY) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {originX, originY};
		q.add(pos);
		visited[originX][originY] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			for(int i=0; i<4; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				
				if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny]) {
					if(map[nx][ny] == '*') continue; // 벽이면 못 가
					if(map[nx][ny] == '.' || map[nx][ny] == '$') {
						int[] next = {nx,ny};
						visited[nx][ny] = true;
						q.add(next);
					} else {
						// 만약 가려는 곳에 키가 있다면
						if(map[nx][ny] >= 'a' && map[nx][ny] <= 'z') {
							key[map[nx][ny]-'a'] = true;
							map[nx][ny] = '.';
							return true;
						} else {
							// 만약 문이 있다면, 키가 있으면 문을 따주자
							if(key[map[nx][ny]-'A']) {
								map[nx][ny] = '.';
								return true;
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	private boolean getDocuments(int originX, int originY) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {originX, originY};
		q.add(pos);
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			visited[x][y] = true;
			if(map[x][y] == '$') {
				answer++;
				map[x][y] = '.';
				return true;
			}

			for(int i=0; i<4; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				
				if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny]) {
					if(map[nx][ny] == '$' || map[nx][ny] == '.') {
						visited[nx][ny] = true;
						int[] next = {nx, ny};
						q.add(next);
					}
				}
			}
		}
		
		return false;
	}

	private void process() throws IOException {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(i!=1 && i!=N && j!=1 && j!=M) continue;
				if(map[i][j]!='*') {
					// 만약 외곽이 문인데 키가 없으면 pass
					if(map[i][j] >= 'A' && map[i][j] <= 'Z') {
						if(!key[map[i][j]-'A']) continue;
					}
					if(getKeys(i,j)) {
						i=1;
						j=0;
					}
					visited = new boolean[N+1][M+1];
				}
			}
		}
//		printMap();
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(i!=1 && i!=N && j!=1 && j!=M) continue;
				// 벽이면 못 가
				if(map[i][j]!='*') {
					// 만약 외곽이 문인데 키가 없으면 pass
					if(map[i][j] >= 'A' && map[i][j] <= 'Z') {
						if(!key[map[i][j]-'A']) continue;
					}

					if(getDocuments(i, j)) {
						i=1;
						j=0;
					}
					visited = new boolean[N+1][M+1];
				}
			}
		}
		
		System.out.println(answer);
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		while(T-->0) {
			input();
			process();
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

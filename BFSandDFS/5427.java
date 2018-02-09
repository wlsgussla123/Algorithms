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
	private int[][] answer_map;
	private boolean[][] visited;
	private int sx, sy;
	private int answer;
	private final int INF = 987654321;
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
		answer_map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
		answer = 0;
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=M; j++) {
				map[i][j] = input[j-1];
				if(input[j-1] == '@') {
					sx = i;
					sy = j;
				}
				if(input[j-1] == '#') {
					answer_map[i][j] = INF;
				}
				if(input[j-1] == '.') {
					answer_map[i][j] = INF-1;
				}
			}
		}
	}
	
	private void bfs(int sx, int sy) {
		Queue<int[]> fq = new LinkedList<int[]>();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(map[i][j] == '*') {
					int[] pos = {i,j,0};
					visited[i][j] = true;
					fq.add(pos);
				}
			}
		}

		while(!fq.isEmpty()) {
			int[] cur = fq.poll();
			int x = cur[0];
			int y = cur[1];
			int cnt = cur[2];
			visited[x][y] = true;
			answer_map[x][y] = cnt;
			
			for(int i=0; i<4; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny] && map[nx][ny] != '#') {
					visited[nx][ny] = true;
					int[] next = {nx, ny, cnt+1};
					fq.add(next);
				}
			}
		}
		visited = new boolean[N+1][M+1];
		
//		for(int i=1; i<=N; i++) {
//			for(int j=1; j<=M; j++) {
//				System.out.print(answer_map[i][j]);
//			}
//			System.out.println();
//		}

		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {sx,sy,0};
		q.add(pos);
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int cnt = cur[2];
			visited[x][y] = true;
			for(int i=0; i<4; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				if(!(nx>=1 && nx<=N && ny>=1 && ny<=M)) {
					answer = cnt+1;
					return;
				}
				
				if(answer_map[nx][ny] == INF) continue;
				if(answer_map[nx][ny] <= cnt+1) continue; 
	
				if(map[nx][ny] != '#' && !visited[nx][ny]) {
					int[] next = {nx,ny,cnt+1};
					visited[nx][ny] = true;
					q.add(next);
				}
			}
		}
	}

	private void process() throws IOException {
		bfs(sx,sy);
		if(answer == 0) System.out.println("IMPOSSIBLE");
		else System.out.println(answer);
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		while(T-->0) {
			input();
			process();
		}
	}
	
	private void print() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				System.out.print(map[i][j]);
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

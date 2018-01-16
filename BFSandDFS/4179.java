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
	private int answer = 100001;
	private Queue<int[]> jq = new LinkedList<int[]>();
	private Queue<int[]> fq = new LinkedList<int[]>();
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[1001][1001];
		visited = new boolean[1001][1001];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			String str = st.nextToken();
			char[] input = new char[str.length()];
			input = str.toCharArray();
			
			for(int j=1; j<=M; j++) {
				map[i][j] = input[j-1];
				if(map[i][j] == 'J') {
					int[] pos = {i,j,0};
					jq.add(pos);
				} else if(map[i][j] == 'F') {
					int[] pos = {i,j};
					fq.add(pos);
				}
			}
		}
	}
	
	private void bfs() {
		while(!jq.isEmpty()) {
			int fqSize = fq.size();
			for(int a=1; a<=fqSize; a++) {
				int[] cur = fq.poll();
				int x = cur[0];
				int y = cur[1];
				
				for(int i=0; i<4; i++) {
					int nx = x + dirs[i][0];
					int ny = y + dirs[i][1];
					
					if(nx>=1 && nx<=N && ny>=1 && ny<=M && map[nx][ny] != '#' && map[nx][ny] != 'F') {
						map[nx][ny] = 'F';
						int[] next = {nx, ny};
						fq.add(next);
					}
				}
			}
			
			int jqSize = jq.size();
			for(int b=1; b<=jqSize; b++) {
				int[] cur = jq.poll();
				int x = cur[0];
				int y = cur[1];
				int c = cur[2];
				
				for(int i=0; i<4; i++) {
					int nx = x + dirs[i][0];
					int ny = y + dirs[i][1];
					
					if(nx<1 || nx>N || ny<1 || ny>M) {
						System.out.println(c+1);
						return;
					}
					
					if(map[nx][ny] != 'F' && map[nx][ny] != '#' && map[nx][ny] != 'J') {
						map[nx][ny] = 'J';
						int[] next = {nx,ny,c+1};
						jq.add(next);						
					}
				}
			}
		}
		
		System.out.println("IMPOSSIBLE");
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

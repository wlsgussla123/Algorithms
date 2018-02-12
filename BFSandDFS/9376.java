package algo; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int row;
	int col;
	public Node(int r, int c) {
		this.row = r;
		this.col = c;
	}
}

class Task {
	private final int BLANK = 0;
	private final int WALL = -1;
	private final int PRISON = 1;
	private final int DOOR = 2;
	private final int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	
	private int[][] map;
	private int N,M;
	private Node helper;
	private Node prison1;
	private Node prison2;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		// 외곽을 한 번 더 둘러치자. (외부를 표현하기 위하여)
		map = new int[N+2][M+2];
		helper = new Node(0,0);
		prison1 = null;
		prison2 = null;
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
				switch(input[j-1]) {
					case '.':
						map[i][j] = BLANK;
						break;
					case '*':
						map[i][j] = WALL;
						break;
					case '$':
						map[i][j] = PRISON;
						if(prison1 == null) {
							prison1 = new Node(i,j);
						} else {
							prison2 = new Node(i,j);
						}
						break;
					case '#':
						map[i][j] = DOOR;
						break;
				}
			}
		}
	}
	
	private int[][] bfs(Node src) {
		int[][] dist = new int[N+2][M+2];
		for(int i=0; i<=N+1; i++) {
			Arrays.fill(dist[i], -1);
		}
		
		Queue<Node> q = new LinkedList<Node>();
		q.add(src);
		dist[src.row][src.col] = 0;
		while(!q.isEmpty()) {
			Node u = q.poll();
			int x = u.row;
			int y = u.col;
			for(int i=0; i<4; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				
				if(nx<0 || nx>N+1 || ny<0 || ny>M+1) continue;
				if(map[nx][ny] == WALL) continue;
				
				if(map[nx][ny] == BLANK || map[nx][ny] == PRISON) {
					if(dist[nx][ny] == -1 || dist[nx][ny] > dist[x][y]) {
						dist[nx][ny] = dist[x][y];
						q.add(new Node(nx,ny));
					}
				} else if(map[nx][ny] == DOOR) {
					if(dist[nx][ny] == -1 || dist[nx][ny] > dist[x][y] + 1) {
						dist[nx][ny] = dist[x][y] + 1;
						q.add(new Node(nx,ny));
					}
				}
			}
		}
		
		return dist;
	}
	
	private void process() {
		int[][] dist1 = bfs(helper);
		int[][] dist2 = bfs(prison1);
		int[][] dist3 = bfs(prison2);
		int answer = 987654321;
		int tempCost = 0;
		
		for(int i=0; i<=N+1; i++) {
			for(int j=0; j<=M+1; j++) {
				if(map[i][j] == WALL) continue;
				tempCost = dist1[i][j] + dist2[i][j] + dist3[i][j];
				if(map[i][j] == DOOR) tempCost -= 2;
				
				answer = answer > tempCost ? tempCost : answer;
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
	
	private void print() {
		for(int i=0; i<=N+1; i++) {
			for(int j=0; j<=M+1; j++) {
				System.out.printf("%3d", map[i][j]);
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

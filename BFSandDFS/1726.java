package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;
	int d;
	public Node(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}

class Task {
	private int N,M;
	private int[][] map;
	private boolean[][][][] visited;
	private int[][][][] dist;
	private Node start;
	private Node end;
	private final int MAX = 100000, BLANK = 0, WALL = 1;
	private int answer = MAX;
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][M+1];
		// k가 0,1,2,3이면 각 칸수만큼 이동
		// 1,2,3,4, = 동서남북
		visited = new boolean[N+1][M+1][4][5];
		dist = new int[N+1][M+1][4][5];
	}
	
	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = getStringTokenizer();
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		start = new Node(x,y,d);
		st = getStringTokenizer();
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		end = new Node(x,y,d);
	}
	
	private int changeDir(int index, int dir) {
		// 동 & 왼
		if((dir == 1 && index == 1) || (dir == 2 && index == 2)) {
			return 4;
		} else if((dir == 1 && index == 2) || (dir == 2 && index == 1)) {
			return 3;
		} else if((dir == 3 && index == 1) || (dir == 4 && index == 2)) {
			return 1;
		} else {
			return 2;
		}
	}
	
	private void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] a = {start.x, start.y, 0, start.d, 0}; // 좌표, k, 방향, cnt
		q.add(a);
		dist[start.x][start.y][0][start.d] = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int k = cur[2];
			int d = cur[3];
			int cnt = cur[4];
			if(cnt >= answer) continue;
			visited[x][y][k][d] = true;
		
			if(x == end.x && y == end.y) {
				if(d == end.d) {
					answer = answer < cnt ? answer : cnt;
				} else {
					if((d == 1 && end.d == 2) || (d == 2 && end.d == 1) || 
					   (d == 3 && end.d == 4) || (d == 4 && end.d == 3)) {
						cnt += 2;
						answer = answer < cnt ? answer : cnt;
					} else {
						cnt += 1;
						answer = answer < cnt ? answer : cnt;
					}
				}
			}
			
			// 일단 방향전환 할 수 있는 것들 담아두고
			for(int i=1; i<=2; i++) {
				int nd = changeDir(i, d);
				if(!visited[x][y][k][nd]) {
					int[] next = {x,y,k,nd,cnt+1};
					visited[x][y][k][nd] = true;
					q.add(next);
				}
			}
			
			// 이동한다.
			for(int i=1; i<=3; i++) {
				int nx = 0;
				int ny = 0;
				boolean isOk = false;
				
				for(int j=1; j<=i; j++) {
					if(d == 1) {
						nx = x;
						ny = y+j;
					} else if(d == 2) {
						nx = x;
						ny = y-j;
					} else if(d == 3) {
						nx = x+j;
						ny = y;
					} else {
						nx = x-j;
						ny = y;
					}
					
					if(nx >= 1 && nx <= N && ny >= 1 && ny <= M && !visited[nx][ny][j][d]) {
						if(map[nx][ny] == WALL) break;
						isOk = true;
					}
				}
				
				if(isOk) {
					visited[nx][ny][i][d] = true;
					int[] next = {nx,ny,i,d,cnt+1};
					q.add(next);
				}
			}
		}
	}

	public void run() throws IOException {
		input();
		bfs();
		System.out.println(answer);
	}
	
	private void print() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
		System.out.println(start.x + " " + start.y + " " + start.d);
		System.out.println(end.x + " " + end.y + " " + end.d);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

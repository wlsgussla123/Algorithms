package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Position {
	int x;
	int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Task {
	private int N,M;
	private int[][] map;
	private final int X[] = {0,0,1,-1};
	private final int Y[] = {1,-1,0,0};
	private boolean[][] visited;
	private Queue<Position> q = new LinkedList<>();
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private boolean checkInner(int x, int y) {
		for(int i=0; i<4; i++) {
			int nx = x + X[i];
			int ny = y + Y[i];
			
			if(nx<1 || nx>N || ny<1 || ny>M) return true;
			if(map[nx][ny] == 1 || visited[nx][ny]) continue;
			visited[nx][ny] = true;
			if(checkInner(nx, ny)) return true;
		}
		return false;
	}
	
	private boolean isContinue() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(map[i][j] == 1) return true;
			}
		}
		
		return false;
	}
	
	private void solution() throws IOException {
		int time = 1;
		while(true) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(map[i][j] == 1) continue;
					visited = new boolean[N+1][M+1];
					if(checkInner(i,j)) {
						visited[i][j] = true;
						map[i][j] = 2;
					}
				}
			}
			
			int cnt = 0;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(map[i][j] != 1) continue;
					cnt = 0;
					for(int k=0; k<4; k++) {
						int nx = i + X[k];
						int ny = j + Y[k];
						if(nx<1 || nx>N || ny<1 || ny>M) {
							cnt++;
							continue;
						}
						
						if(map[nx][ny] == 2) cnt++;
						if(cnt>=2) {
							q.add(new Position(i, j));
							break;
						}
					}
				}
			}
			
			while(!q.isEmpty()) {
				Position p = q.poll();
				map[p.x][p.y] = 2;
			}
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(map[i][j] != 0) continue;
					visited = new boolean[N+1][M+1];
					if(checkInner(i,j)) {
						visited[i][j] = true;
						map[i][j] = 2;
					}
				}
			}
			
			if(!isContinue()) break;
			time++;
		}
		
		bw.write(String.valueOf(time));
	}
	
	public void run() throws IOException {
		input();
		solution();
		close();
	}
	
	private void print() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private void close() throws IOException {
		br.close();
		bw.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

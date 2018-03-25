package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[][] map;
	private int rain = 0;
	private int answer = 0;
	private final int[] dx = {0,0,1,-1};
	private final int[] dy = {1,-1,0,0};
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(rain < map[i][j]) rain = map[i][j];
			}
		}
	}
	
	private boolean checkArea(int x, int y) {
		return (x>=1 && x<=N && y>=1 && y<=N);
	}
	
	private void safeZone(int x, int y, int[][] map, boolean[][] visited) {
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(checkArea(nx, ny) && !visited[nx][ny] && map[nx][ny] != 0) {
				visited[nx][ny] = true;
				safeZone(nx, ny, map, visited);
			}
		}
	}
	
	private int getSafeZone(int[][] map) {
		int cnt = 0;
		boolean[][] visited = new boolean[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(visited[i][j] || map[i][j] == 0) continue;
				visited[i][j] = true;
				safeZone(i, j, map, visited);
				cnt++;
			}
		}
		
		return cnt;
	}
	
	private void dfs(int[][] map, int rain) {
		int[][] temp = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		// 침수 시키기.
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(temp[i][j] <= rain) {
					temp[i][j] = 0;
				}
			}
		}
		
		int safe = getSafeZone(temp);
		answer = answer > safe ? answer : safe;
	}
	
	private void solution() {
		for(int i=0; i<=rain; i++) {
			dfs(map, i);
		}
		System.out.println(answer);
	}
	
	public void run() throws IOException {
		input();
		solution();
	}
	
	private void print(int[][] map) {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private void close() throws IOException {
		bw.close();
		br.close();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

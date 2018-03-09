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
	private int[][] dist;
	private int[] X = {0,0,1,-1};
	private int[] Y = {1,-1,0,0};
	private int answer = -1;
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		dist = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private void solution(int x, int y, int leaf) {
		if(dist[x][y] > answer) answer = dist[x][y];
		
		for(int i=0; i<4; i++) {
			int nx = x + X[i];
			int ny = y + Y[i];
			
			if(nx<1 || nx>N || ny<1 || ny>N) continue;
			if(leaf < map[nx][ny] && dist[x][y] + 1 > dist[nx][ny]) {
				dist[nx][ny] = dist[x][y]+1;
				solution(nx, ny, map[nx][ny]);
			}
		}
	}
	
	public void run() throws IOException {
		input();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(dist[i][j] == 0) {
					dist[i][j] = 1;
					solution(i, j, map[i][j]);
				}
			}
		}
		System.out.println(answer);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

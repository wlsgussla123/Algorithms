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
	private int[][] dist;
	private final int[] X = {1,0,1};
	private final int[] Y = {0,1,1};
	private int answer = -1;
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
		dist = new int[N+1][M+1];
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
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				dist[i][j] = map[i][j];
			}
		}
	}
	
	public void run() throws IOException {
		input();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				dist[i][j] = Math.max(dist[i][j-1], Math.max(dist[i-1][j], dist[i-1][j-1])) + map[i][j];
			}
		}
		bw.write(String.valueOf(dist[N][M]));
		close();
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

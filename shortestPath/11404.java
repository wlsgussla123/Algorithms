package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private int N;
	private int M;
	private int[][] map;
	
	private final int INF = 987654321;
	private final int MAX_COST = 100000;
	
	private void init() {
		map = new int[N+1][N+1];
		for(int i=0; i<=N; i++) {
			for(int j=0; j<=N; j++) {
				map[i][j] = INF;
			}
			map[i][i] = 0; // 자기자신은 0
		}
	}
	
	private void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		init();
		
		for(int i=1; i<=M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			while(st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				if(map[from][to] > cost) {
					map[from][to] = cost;
				}
			}
		}
	}
	
	private void floydWarshall() {
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
	}
	
	private void print() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j] > MAX_COST)
					System.out.print(0 + " ");
				else
					System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void run() throws IOException {
		input();
		floydWarshall();
		print();
	}
}

public class main {		
	public static void main(String args[]) throws IOException {
		Task task = new Task();
		task.run();
	}
}

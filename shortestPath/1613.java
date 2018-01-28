package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N,K;
	private int[][] map;
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=K; i++) {
			st = getStringTokenizer();
			int before = Integer.parseInt(st.nextToken());
			int after = Integer.parseInt(st.nextToken());
			map[before][after] = -1;
			map[after][before] = 1;
		}
	}
	
	private void floydWarshall() {
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(map[i][j] != 0) continue;
					if(map[i][k] == -1 && map[k][j] == -1) {
						map[i][j] = -1;
						map[j][i] = 1;
					}
					if(map[i][k] == 1 && map[k][j] == 1) {
						map[i][j] = 1;
						map[j][i] = -1;
					}
				}
			}
		}
	}
	
	private void infer() throws IOException {
		st = getStringTokenizer();
		int M = Integer.parseInt(st.nextToken());
		for(int i=0; i<M; i++) {
			st = getStringTokenizer();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(map[a][b]);
		}
	}

	public void run() throws IOException {
		input();
		floydWarshall();
		infer();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

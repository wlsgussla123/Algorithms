package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Task {
	private int N,M,K;
	private int[][] map;
	private int[][] answer_map;
	private int answer;
//	private boolean[][] visited;
//	private int[] X = {0,0,1,-1};
//	private int[] Y = {1,-1,0,0};
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
		answer_map = new int[N+1][N+1];
		answer = -1;
//		visited = new boolean[N+1][N+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private void service(int x, int y, int k) {
		// 위쪽
		int idx = k;
		for(int i=x-1; i>x-k; i--) {
			if(i<1 || i>N) break;
			idx--;
			for(int j=y; j<y+idx; j++) {
				if(j<1 || j>N) break;
				answer_map[i][j] = 1;
			}

			for(int j=y; j>y-idx; j--) {
				if(j<1 || j>N) break;
				answer_map[i][j] = 1;
			}
		}
		
		// 아래쪽
		idx = k;
		for(int i=x+1; i<x+k; i++) {
			if(i<1 || i>N) break;
			
			idx--;
			for(int j=y; j<y+idx; j++) {
				if(j<1 || j>N) break;
				answer_map[i][j] = 1;
			}

			for(int j=y; j>y-idx; j--) {
				if(j<1 || j>N) break;
				answer_map[i][j] = 1;
			}
		}
		
		// 중앙
		for(int j=y; j<y+k; j++) {
			if(j<1 || j>N) break;
			answer_map[x][j] = 1;
		}
		for(int j=y; j>y-k; j--) {
			if(j<1 || j>N) break;
			answer_map[x][j] = 1;
		}
	}
	
	private void benefit(int k) {
		int cnt = 0;
		int op_cost = k*k + (k-1) * (k-1);
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j]==1 && map[i][j] == answer_map[i][j]) {
					cnt++;
				}
			}
		}
		
		if((cnt * M) - op_cost >= 0) {
			if(cnt > answer) answer = cnt;
		}
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		int idx = 1;
		while(idx<=T) {
			input();
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					for(int k=1; k<=N+1; k++) {
						answer_map = new int[N+1][N+1];
						service(i,j,k);
						benefit(k);
					}
				}
			}
			System.out.println("#"+idx + " " + answer);			
			idx++;
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

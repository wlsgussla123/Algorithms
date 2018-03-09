package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Task {
	private int[][] map;
	private boolean[] visited;
	private boolean answer;
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[9][9];
		visited = new boolean[10];
		answer = true;
	}
	
	private void input() throws IOException {
		init();
		for(int i=0; i<9; i++) {
			st = getStringTokenizer();
			for(int j=0; j<9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private void solution() {
		for(int i=0; i<9; i++) {
			visited = new boolean[10];
			for(int j=0; j<9; j++) {
				int num = map[i][j];
				if(visited[num]) {
					answer = false;
					return;
				}
				visited[num] = true;
			}
		}
		
		for(int i=0; i<9; i++) {
			visited = new boolean[10];
			for(int j=0; j<9; j++) {
				int num = map[j][i];
				if(visited[num]) {
					answer = false;
					return;
				}
				visited[num] = true;
			}
		}
		
		for(int i=0; i<9; i+=10) {
			for(int j=0; j<9; j+=10) {
				visited = new boolean[10];
				for(int n=i; n<i+3; n++) {
					for(int m=j; m<j+3; m++) {
						int num = map[n][m];
						if(visited[num]) {
							answer = false;
							return;
						}
						visited[num] = true;
					}					
				}
			}
		}
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		int idx = 1;
		while(idx<=T) {
			input();
			solution();
			if(answer) System.out.println("#"+idx+" 1");
			else System.out.println("#"+idx+" 0");
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
/*
4 5 7 1 6 3 8 2 9
6 3 9 8 2 7 5 4 1
7 9 3 4 8 5 1 6 2
1 8 2 5 4 9 6 3 7
8 6 1 7 9 2 3 5 4
5 2 4 6 3 1 7 9 8
3 7 6 9 1 4 2 8 5
2 4 5 3 7 8 9 1 6
9 1 8 2 5 6 4 7 3
*/

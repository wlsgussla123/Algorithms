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
	private int answer = 0;
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
	}
	
	private boolean check() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(!visited[i][j]) return false;
			}
		}
		return true;
	}
	
	private void solution(int row, int col, int dir) {
		if(dir == 1) {
			int i=col;
			for(i=col; i<=M; i++) {
				if(!visited[row][i])
					visited[row][i] = true;
				else 
					break;
			}
			if(check()) return;
			answer++;
			i--;
			solution(row+1, i, 3);
		} else if(dir == 2) {
			int i=col;
			for(i=col; i>=1; i--) {
				if(!visited[row][i])
					visited[row][i] = true;
				else 
					break;
			}
			if(check()) return;
			answer++;
			i++;
			solution(row-1, i, 4);
		} else if(dir == 3) {
			int i=row;
			for(i=row; i<=N; i++) {
				if(!visited[i][col])
					visited[i][col] = true;
				else 
					break;
			}
			if(check()) return;
			answer++;
			i--;
			solution(i, col-1, 2);
		} else {
			int i=row;
			for(i=row; i>=1; i--) {
				if(!visited[i][col])
					visited[i][col] = true;
				else
					break;
			}
			if(check()) return;
			answer++;
			i++;
			solution(i, col+1, 1);
		}
	}
	
	public void run() throws IOException {
		input();
		solution(1,1,1); // dir = 1,2,3,4 (동서남북)
		System.out.println(answer);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

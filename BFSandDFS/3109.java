package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int R,C;
	private char[][] map;
	private boolean[][] visited;
	private int[] X = {-1,0,1};
	private int[] Y = {1,1,1};
	private int answer = 0;
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[R+1][C+1];
		visited = new boolean[R+1][C+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=R; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=C; j++) {
				map[i][j] = input[j-1];
			}
		}
	}
	
	private boolean solution(int x, int y) {
		if(y==C) return true;
		
		for(int i=0; i<3; i++) {
			int nx = x + X[i];
			int ny = y + Y[i];
			
			if(nx<1 || nx>R || ny<1 || ny>C || visited[nx][ny]) continue;
			if(map[nx][ny] == 'x') continue;
			visited[nx][ny] = true;
			if(solution(nx, ny)) return true;
		}
		return false;
	}
	
	public void run() throws IOException {
		input();
		for(int i=1; i<=R; i++) {
			visited[i][1] = true;
			if(solution(i, 1)) answer++;
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


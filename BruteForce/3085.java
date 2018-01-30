package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	private char[][] map;
	private char[][] copyMap;
	private int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
	private int answer = 0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[N+1][N+1];
		copyMap = new char[N+1][N+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=N; j++) {
				map[i][j] = input[j-1];
				copyMap[i][j] = input[j-1];
			}
		}
	}

	private void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	
	private void clear() {
		for(int i=1; i<=N; i++)
			for(int j=1; j<=N; j++)
				copyMap[i][j] = map[i][j];
	}
	
	private void eat() {
		int cnt = 1;
		for(int i=1; i<=N; i++) {
			cnt = 1;
			for(int j=2; j<=N; j++) {
				if(copyMap[i][j] == copyMap[i][j-1]) {
					cnt++;
				} else {
					if(cnt > answer) answer = cnt;
					cnt = 1;
				}
			}
			if(cnt > answer) answer = cnt;
		}
		
		cnt = 1;
		for(int j=1; j<=N; j++) {
			cnt = 1;
			for(int i=2; i<=N; i++) {
				if(copyMap[i][j] == copyMap[i-1][j]) {
					cnt++;
				} else {
					if(cnt > answer) answer = cnt;
					cnt = 1;
				}
			}
			if(cnt > answer) answer = cnt;
		}
	}
	
	private void dfs(int x, int y) {
		for(int i=0; i<4; i++) {
			int nx = x + dirs[i][0];
			int ny = y + dirs[i][1];
			
			if(nx>=1 && nx<=N && ny>=1 && ny<=N) {
				char color = copyMap[nx][ny];
				copyMap[nx][ny] = copyMap[x][y];
				copyMap[x][y] = color;
				eat();
				clear();
			}
		}
	}

	private void process() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				dfs(i,j);
			}
		}
		System.out.println(answer);
	}

	public void run() throws IOException {
		input();
		process();
		close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

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
	private int[][] answer_map;
	private boolean[][] visited;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[N+1][N+1];
		answer_map = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
	}
	
	// 입력의 depth는 매우 잘 나오는 편
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=N; j++) {
				map[i][j] = input[j-1];
			}
		}
	}
	
	private void clear() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				visited[i][j] = false;
				answer_map[i][j] = 0;
			}
		}
	}
	
	private void getAnswer() {
		int max = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(answer_map[i][j] > max) max = answer_map[i][j];
			}
		}
		System.out.print(max + " ");		
	}
		
	private void weakColor(int x, int y, char color, int cnt) {
		answer_map[x][y] = cnt;
		for(int i=0; i<4; i++) {
			int nx = x + dirs[i][0];
			int ny = y + dirs[i][1];
			if(nx>=1 && nx<=N && ny>=1 && ny<=N && !visited[nx][ny]) {
				if(color == 'B' && map[nx][ny] != 'B') continue;
				if((color == 'R' && map[nx][ny] == 'B') || (color == 'G' && map[nx][ny] == 'B')) continue;
				visited[nx][ny] = true;
				weakColor(nx, ny, color, cnt);
			}
		}
	}
	
	private void normalColor(int x, int y, char color, int cnt) {
		answer_map[x][y] = cnt;
		for(int i=0; i<4; i++) {
			int nx = x + dirs[i][0];
			int ny = y + dirs[i][1];
			if(nx>=1 && nx<=N && ny>=1 && ny<=N && !visited[nx][ny]) {
				if(map[nx][ny] == color) {
					visited[nx][ny] = true;
					normalColor(nx, ny, color, cnt);
				}
			}
		}
	}
	
	private void process() throws IOException {
		int cnt = 1;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					normalColor(i,j,map[i][j],cnt++);
				}
			}
		}
		getAnswer();
		clear();
		
		cnt = 1;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					weakColor(i,j,map[i][j],cnt++);
				}
			}
		}
		getAnswer();
	}
	
	public void run() throws IOException {
		input();
		process();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

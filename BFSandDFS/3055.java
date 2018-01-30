package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private char[][] map;
	private int[][] answer_map;
	private boolean[][] visited;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	private int[] S = new int[2];
	private int[] D = new int[2];
	private int answer = 0;
	private final int MAX = 987654321;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[N+1][M+1];
		visited = new boolean[N+1][M+1];
		answer_map = new int[N+1][M+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				answer_map[i][j] = MAX;
			}
		}
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=M; j++) {
				map[i][j] = input[j-1];
				if(input[j-1] == 'S') {
					S[0] = i;
					S[1] = j;
				} else if(input[j-1] == 'D') {
					D[0] = i;
					D[1] = j;
				}
			}
		}
	}

	private void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void clear() {
		for(int i=1; i<=N; i++)
			for(int j=1; j<=M; j++) visited[i][j] = false;
	}
	
	private void bfs() {
		Queue<int[]> rainQ = new LinkedList<int[]>();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(map[i][j] == '*') {
					int[] rain = {i,j,1};
					visited[i][j] = true;
					rainQ.add(rain);
				}
			}
		}
		
		while(!rainQ.isEmpty()) {
			int[] rain = rainQ.poll();
			int rx = rain[0];
			int ry = rain[1];
			int rc = rain[2];
			answer_map[rx][ry] = rc;
			
			for(int i=0; i<4; i++) {
				int nx = rx + dirs[i][0];
				int ny = ry + dirs[i][1];
				
				if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny] && map[nx][ny] != 'X' && map[nx][ny] != 'D') {
					int[] next = {nx, ny, rc+1};
					visited[nx][ny] = true;
					rainQ.add(next);
				}
			}
		}
		clear();

		Queue<int[]> hQ = new LinkedList<int[]>();
		int[] h = {S[0], S[1], 1}; // 고슴도치 시작점, 1로 시작하는 이유는 rain의 넘버를 1부터 채웠기 때문에 비교하기 위하여.
		hQ.add(h);
		while(!hQ.isEmpty()) {
			int[] hedgehog = hQ.poll();
			int hx = hedgehog[0];
			int hy = hedgehog[1];
			int hc = hedgehog[2];
			visited[hx][hy] = true;
			
			if(hx == D[0] && hy == D[1]) {
				answer = hc-1;
				break;
			}
			
			for(int i=0; i<4; i++) {
				int nx = hx + dirs[i][0];
				int ny = hy + dirs[i][1];
				
				if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny] && (map[nx][ny] == '.' || map[nx][ny] == 'D')) {
					if(answer_map[nx][ny] > hc+1) {
						int[] next = {nx,ny,hc+1};
						visited[nx][ny] = true;
						hQ.add(next);
					}
				}
			}
		}
		System.out.println(answer == 0 ? "KAKTUS" : answer);
	}
	
	public void run() throws IOException {
		input();
		bfs();
		close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

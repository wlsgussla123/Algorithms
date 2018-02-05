package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[][] map;
	private int[][] answer_map;
	private int[] mArr;
	private boolean[][] visited;
	private boolean hasHammer = true;
	private int extendCnt = 1;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][M+1];
		answer_map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				answer_map[i][j] = map[i][j];
			}
		}
	}
	
	private void countRoom(int x, int y, int cnt) {
		answer_map[x][y] = cnt;
		for(int i=0; i<4; i++) {
			if(map[x][y] == 15) continue;
			if(i==0 && (map[x][y] == 4 || map[x][y] == 5 || map[x][y] == 6 || map[x][y] == 7 || map[x][y] == 12 || map[x][y] == 13 || map[x][y] == 14)) continue;
			if(i==1 && (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 5 || map[x][y] == 7 || map[x][y] == 9 || map[x][y] == 11 || map[x][y] == 13)) continue;
			if(i==2 && (map[x][y] == 8 || map[x][y] == 9 || map[x][y] == 10 || map[x][y] == 11 || map[x][y] == 12 || map[x][y] == 13 || map[x][y] == 14)) continue;
			if(i==3 && (map[x][y] == 2 || map[x][y] == 3 || map[x][y] == 6 || map[x][y] == 7 || map[x][y] == 10 || map[x][y] == 11 || map[x][y] == 14)) continue;

			int nx = x + dirs[i][0];
			int ny = y + dirs[i][1];
			if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny]) {
				visited[nx][ny] = true;
				countRoom(nx, ny, cnt);
			}
		}
	}
	
	private int bigRoom(int len) {
		mArr = new int[len];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				mArr[answer_map[i][j]]++;
			}
		}
		
		int max = 0;
		for(int i=1; i<len; i++) {
			if(mArr[max] < mArr[i]) max = i;
		}
		return mArr[max];
	}
	
	private int breakWall(int x, int y) {
		int max = -1;
		for(int i=0; i<4; i++) {
			int nx = x + dirs[i][0];
			int ny = y + dirs[i][1];
			
			if(nx>=1 && nx<=N && ny>=1 && ny<=M) {
				int r1 = answer_map[x][y];
				int r2 = answer_map[nx][ny];
				if(r1 != r2) {
					int sum = mArr[answer_map[x][y]] + mArr[answer_map[nx][ny]];
					max = Math.max(max, sum);
				}
			}
		}
		
		return max;
	}
	
	private void process() throws IOException {
		int cnt = 1;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(!visited[i][j]) {
					visited[i][j] = true;
					countRoom(i, j, cnt++);
				}
			}
		}
		System.out.println(cnt-1); // 방의 개수
		System.out.println(bigRoom(cnt));
		
		int max = -1;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				max = Math.max(max, breakWall(i,j));
			}
		}
		System.out.println(max);
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

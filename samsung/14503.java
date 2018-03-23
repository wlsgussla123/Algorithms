package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,M,A,B,D;
	private int[][] map;
	private int answer = 0;
	private final int BLANK = 0, WALL = 1, CLEAN = 2;
	// 북동남서
	private final int[] dx = {-1,0,1,0};
	private final int[] dy = {0,1,0,-1};
	private StringTokenizer st = null;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N][M];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		st = getStringTokenizer();
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	// 북동남서
	private int rotateDir(int dir) {
		if(dir == 0) {
			return 3;
		} else if(dir == 1) {
			return 0;
		} else if(dir == 2) {
			return 1;
		} else {
			return 2;
		}
	}
	
	private void dfs(int x, int y, int dir) {
		// 현재 위치 청소
		// 맵을 청소한 상태를 넣어줌
		if(map[x][y] == BLANK) {
			answer++;
			map[x][y] = CLEAN;
		}
		
		boolean isGoing = false;
		// 일단 4번 회전해봐
		for(int i=0; i<4; i++) {
			dir = rotateDir(dir);
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			// 회전했는데 청소할 곳이라면
			if(nx>=0 && nx<N && ny>=0 && ny<M && map[nx][ny] == BLANK) {
				dfs(nx, ny, dir);
				isGoing = true;
				break;
			}
		}
		
		// 가지 못 했으면 후진해야 한다.
		if(!isGoing) {
			int nx = 0;
			int ny = 0;
			if(dir == 0) {
				// 북의 후진 -> 남
				nx = x+1;
				ny = y;
			} else if(dir == 1) {
				// 동의 후진 -> 서
				nx = x;
				ny = y-1;
			} else if(dir == 2) {
				// 남의 후진 - > 북
				nx = x-1;
				ny = y;
			} else {
				// 서의 후진 -> 동
				nx = x;
				ny = y+1;
			}
			
			if(nx>=0 && nx<N && ny>=0 && ny<M && map[nx][ny] != WALL) {
				dfs(nx, ny, dir);
			}
		}
	}
	
	public void run() throws IOException {
		input();
		dfs(A,B,D);
		System.out.println(answer);
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

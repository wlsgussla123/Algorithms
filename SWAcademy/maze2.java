package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int[][] map;
	private boolean[][] visited;
	private final int BLANK=0, WALL=1, START=2, DEST=3, N=100;
	private final int[] X = {0,0,1,-1};
	private final int[] Y = {1,-1,0,0};
	private int sx,sy,ex,ey;
	private boolean flag;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
		flag = false;
	}
	
	private void input() throws IOException {
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=N; j++) {
				map[i][j] = input[j-1] - '0';
				if(map[i][j] == START) {
					sx = i;
					sy = j;
				}
				if(map[i][j] == DEST) {
					ex = i;
					ey = j;
				}
			}
		}			
	}
	
	private void solution(int x, int y, boolean[][] visited) {
		boolean[][] temp = new boolean[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				temp[i][j] = visited[i][j];
			}
		}
		
		if(x == ex && y == ey) {
			flag = true;
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nx = x + X[i];
			int ny = y + Y[i];
			
			if(nx>=1 && nx<=N && ny>=1 && ny<=N && !temp[nx][ny]) {
				if(map[nx][ny] == WALL) continue;
				temp[nx][ny] = true;
				solution(nx, ny, temp);
			}
		}
	}
	
	public void run() throws IOException {
		for(int T=1; T<=10; T++) {
			st = getStringTokenizer();
			int idx = Integer.parseInt(st.nextToken());

			input();
			solution(sx, sy, visited);
			if(flag)
				System.out.println("#"+idx+" 1");
			else
				System.out.println("#"+idx+" 0");
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

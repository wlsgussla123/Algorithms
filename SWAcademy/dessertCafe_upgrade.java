package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[][] map;
	private boolean[] number;
	private final int[] X = {-1, 1, 1, -1};
	private final int[] Y = {1, 1, -1, -1};
	private int answer;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		answer = -1;
		map = new int[N+1][N+1];
		number = new boolean[101];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private void dfs(int dir, int x, int y, int ex, int ey, int cnt) {
		if(dir == 3 && x == ex && y == ey) {
			answer = answer > cnt ? answer : cnt;
			return;
		}
		
		if(number[map[x][y]]) return;
		else 
			number[map[x][y]] = true;
		
		if(1 <= x + X[dir] && x + X[dir] <= N && 1 <= y + Y[dir] && y + Y[dir] <= N) {
			dfs(dir, x + X[dir], y + Y[dir], ex, ey, cnt+1);
		}
		if(dir <= 2 && 1 <= x + X[dir+1] && x + X[dir+1] <= N && 1 <= y + Y[dir+1] && y + Y[dir+1] <= N) {
			dfs(dir+1, x + X[dir+1], y + Y[dir+1], ex, ey, cnt+1);
		}
		
		number[map[x][y]] = false;
	}
	
	private void solution() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				dfs(0,i,j,i,j,0);
			}
		}
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		int idx = 1;
		while(idx <= T) {
			input();
			solution();
			System.out.println("#"+idx+" " + answer);
			idx++;
		}
	}
}
 
public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

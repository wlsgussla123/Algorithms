package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[][] map;
	private boolean[][] visited;
	private boolean[] number;
	private int startX;
	private int startY;
	private int answer;
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
		number = new boolean[101];
		answer = 0;
	}
	
	// 입력
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
	
	private boolean check(int x, int y, int dir) {
		int nx = 0;
		int ny = 0;
		if(dir == 1) {
			nx = x-1;
			ny = y+1;
		} else if(dir == 2) {
			nx = x+1;
			ny = y+1;
		} else if(dir == 3) {
			nx = x+1;
			ny = y-1;
		} else {
			nx = x-1;
			ny = y-1;
		}
		
		if(nx>=1 && nx<=N && ny>=1 && ny<=N)
			return true;
		else
			return false;
	}
	
	private void dfs(int x, int y, int dir, int len, int cnt, int inc, int dec) {
		int nx = 0;
		int ny = 0;
		
		if(x == startX && y == startY && cnt != 1) {
			if(answer < cnt-1) answer = cnt-1;
//			for(int i=1; i<=N; i++) {
//				for(int j=1; j<=N; j++) {
//					if(visited[i][j]) {
//						System.out.println(i + " " + j);
//					}
//				}
//			}
//			System.out.println("---------------");
			return;
		}
		
		// 숫자 중복이면 취소
		if(number[map[x][y]]) return; 
		
		if(dir == 1) {
			if(inc != 0 && len == inc) {
				if(len == 1) return;
				dfs(x, y, 2, 1, cnt, len, dec);
			} else {
				if(check(x,y,dir)) {
					nx = x-1;
					ny = y+1;
					if(!visited[nx][ny]) {
						number[map[x][y]] = true;
						visited[nx][ny] = true;
						dfs(nx, ny, dir, len+1, cnt+1, inc, dec);
						visited[nx][ny] = false;
						number[map[x][y]] = false;
					}
				}

				if(len == 1) return;
				dfs(x, y, 2, 1, cnt, len, dec);
			}
		} else if(dir == 2) {
			if(dec != 0 && len == dec) {
				if(len == 1) return;
				dfs(x, y, 3, 1, cnt, inc, len);
			} else {
				if(check(x,y,dir)) {
					nx = x+1;
					ny = y+1;
					if(!visited[nx][ny]) {
						number[map[x][y]] = true;
						visited[nx][ny] = true;
						dfs(nx, ny, dir, len+1, cnt+1, inc, dec);
						visited[nx][ny] = false;
						number[map[x][y]] = false;
					}
				}

				if(len == 1) return;
				dfs(x, y, 3, 1, cnt, inc, len);
			}
		} else if(dir == 3) {
			if(inc != 0 && len == inc) {
				if(len == 1) return;
				dfs(x, y, 4, 1, cnt, len, dec);
			} else {
				if(check(x,y,dir)) {
					nx = x+1;
					ny = y-1;
					if(!visited[nx][ny]) {
						number[map[x][y]] = true;
						visited[nx][ny] = true;
						dfs(nx, ny, dir, len+1, cnt+1, inc, dec);
						visited[nx][ny] = false;
						number[map[x][y]] = false;
					}
				}
		
				if(len == 1) return;
				dfs(x, y, 4, 1, cnt, len, dec);
			}
		} else {
			if(dec != 0 && len == dec) {
				if(len == 1) return;
				dfs(x, y, 1, 1, cnt, inc, len);
			} else {
				if(check(x,y,dir)) {
					nx = x-1;
					ny = y-1;
					if(!visited[nx][ny]) {
						number[map[x][y]] = true;
						visited[nx][ny] = true;
						dfs(nx, ny, dir, len+1, cnt+1, inc, dec);
						visited[nx][ny] = false;
						number[map[x][y]] = false;
					}
				}
				
				if(len == 1) return;
				dfs(x, y, 1, 1, cnt, inc, len);
			}
		}
	}
	
	private void solution() {
		for(int i=2; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				// 1,2,3,4 = 우상, 우하, 좌하, 좌상
				for(int k=1; k<=4; k++) {
					startX = i;
					startY = j;
					dfs(startX,startY,1,1,1,0,0);
				}
			}
		}
		
//		startX = 3;
//		startY = 1;
//		dfs(startX,startY,1,1,1,0,0);
	}

	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		int idx = 1;
		while(idx <= T) {
			input();
			solution();
			if(answer == 0) System.out.println("#"+idx+" -1");
			else System.out.println("#"+idx + " " + answer);
			idx++;
		}
	}
	
	private void print() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int[][] map;
	private boolean[][] visited;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}, {-1,1}, {1,1}, {1,-1}, {-1,-1}}; // 동서남북, 대각 
	private final int N = 19;
	private boolean flag = false;
	private int answer = -1;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
	}

	private void input() throws IOException {
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	// 오목이 세로가 아니라면, 가장 좌측의 좌표 출력. 세로라면 가장 상단의 좌표 출력
	private void getPosition(int x, int y, int d) {
		if(d==0 || d==2 || d==4 || d==5) {
			System.out.println(x + " " + y);
		} else if(d==1) {
			System.out.print(x + " ");
			System.out.println(y-4);
		} else if(d==3) {
			System.out.println(x-4 + " " + y);
		} else if(d==6) {
			System.out.print(x+4 + " ");
			System.out.println(y-4);
		} else if(d==7) {
			System.out.print(x-4 + " ");
			System.out.println(y-4);
		}
	}
	
	// 현재 방향으로 오목인 상태인데, 반대편으로도 돌이 하나라도 더 있으면 6목이상 이므로 오목 불가
	private boolean otherSide(int x, int y, int d, int value) {
		if(d==0) {
			int nx = x;
			int ny = y-1;
			if(nx>=1 && nx<=N && ny>=1 && ny<=N) {
				if(map[nx][ny] == value) return false;
			}
		} else if(d==1) {
			int nx = x;
			int ny = y+1;
			if(nx>=1 && nx<=N && ny>=1 && ny<=N) {
				if(map[nx][ny] == value) return false;
			}			
		} else if(d==2) {
			int nx = x-1;
			int ny = y;
			if(nx>=1 && nx<=N && ny>=1 && ny<=N) {
				if(map[nx][ny] == value) return false;
			}
		} else if(d==3) {
			int nx = x+1;
			int ny = y;
			if(nx>=1 && nx<=N && ny>=1 && ny<=N) {
				if(map[nx][ny] == value) return false;
			}
		} else if(d==4) {
			int nx = x+1;
			int ny = y-1;
			if(nx>=1 && nx<=N && ny>=1 && ny<=N) {
				if(map[nx][ny] == value) return false;
			}
		} else if(d==5) {
			int nx = x-1;
			int ny = y-1;
			if(nx>=1 && nx<=N && ny>=1 && ny<=N) {
				if(map[nx][ny] == value) return false;
			}
		} else if(d==6) {
			int nx = x-1;
			int ny = y+1;
			if(nx>=1 && nx<=N && ny>=1 && ny<=N) {
				if(map[nx][ny] == value) return false;
			}
		} else if(d==7) {
			int nx = x+1;
			int ny = y+1;
			if(nx>=1 && nx<=N && ny>=1 && ny<=N) {
				if(map[nx][ny] == value) return false;
			}
		}
		
		return true;
	}
	
	// 오목인 상태에서 현재 방향으로 돌 하나만 더 놔서 돌이 있다면 6목이므로 불가
	private boolean sameSide(int x, int y, int v, int d) {
		for(int i=0; i<8; i++) {
			int nx = x + dirs[i][0];
			int ny = y + dirs[i][1];
			if(nx>=1 && nx<=N && ny>=1 && ny<=N && !visited[nx][ny]) {
				if(map[nx][ny] != v) continue;
				if(d == i) return false;
			}
		}
		
		return true;
	}
	
	private void bfs(int r, int c, int v) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {r,c,v,1,-1}; // 좌표, 값, cnt, 방향
		visited[r][c] = true;
		q.add(pos);

		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int value = cur[2];
			int cnt = cur[3];
			int dir = cur[4];
			// 돌이 오목이 되었을 때 6목이 되는지 체크한다. 5목이라면 현재 좌표를 출력하고 종료
			if(cnt == 5) {
				if(!sameSide(x,y,value,dir)) continue;
				if(!otherSide(r,c,dir,map[r][c])) continue;
				System.out.println(map[r][c]);
				getPosition(r,c,dir);
				flag = true;
				return;
			}
			
			// 동서남북 대각 모두 검사 (8방향)
			for(int i=0; i<8; i++) {
				int nx = x + dirs[i][0];
				int ny = y + dirs[i][1];
				if(nx>=1 && nx<=N && ny>=1 && ny<=N && !visited[nx][ny]) {
					if(map[nx][ny] != value) continue;
					// 첫 시작은 어느 방향으로 가든 상관없다.
					if(dir==-1) {
						int[] next = {nx,ny,value,cnt+1,i};
						visited[nx][ny] = true;
						q.add(next);
					}
					
					if(dir == i) { 
						int[] next = {nx,ny,value,cnt+1,i};
						visited[nx][ny] = true;
						q.add(next);
					}
				}
			}
		}
	}
	
	private void process() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j] != 0) {
					answer = 1;
					bfs(i,j,map[i][j]);
					if(flag) return;
					visited = new boolean[N+1][N+1];
				}
			}
		}
		System.out.println("0");
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

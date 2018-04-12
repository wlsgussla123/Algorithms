package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Operation {
	int num;
	char op;
	public Operation(int num, char op) {
		this.num = num;
		this.op = op;
	}
}

class Position {
	int x;
	int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,K,L;
		private int[][] map;
		private final int[] dx = {0,0,1,-1};
		private final int[] dy = {1,-1,0,0};
		private final int BLANK = 0, APPLE = 1, SNAKE = 2;
		private Queue<Operation> op = new LinkedList();
		private Queue<Position> q = new LinkedList();
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N+1][N+1];
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			init();
			
			st = getStringTokenizer();
			K = Integer.parseInt(st.nextToken());
			for(int i=0; i<K; i++) {
				st = getStringTokenizer();
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = APPLE;
			}
			
			st = getStringTokenizer();
			L = Integer.parseInt(st.nextToken());
			for(int i=0; i<L; i++) {
				st = getStringTokenizer();
				int n = Integer.parseInt(st.nextToken());
				char d = st.nextToken().charAt(0);
				op.add(new Operation(n, d));
			}
		}
		
		private boolean checkArea(int x, int y) {
			return x>=1 && x<=N && y>=1 && y<=N;
		}
		
		// 방향 전환
		private int changeDir(int dir, char op) {
			if(dir == 0) {
				if(op == 'D') {
					return 2;
				} else {
					return 3;
				}
			} else if(dir == 1) {
				if(op == 'D') {
					return 3;
				} else {
					return 2;
				}
			} else if(dir == 2) {
				if(op == 'D') {
					return 1;
				} else {
					return 0;
				}
			} else {
				if(op == 'D') {
					return 0;
				} else {
					return 1;
				}
			}
		}
		
		private void move(int x, int y, int dir, int cnt) {
			map[x][y] = SNAKE;
			// 방향 바꿔야 되면 바꾸고
			if(!op.isEmpty()) {
				if(cnt == op.peek().num) {
					Operation o = op.poll();
					dir = changeDir(dir, o.op);
				}
			}
			
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if(!checkArea(nx, ny) || map[nx][ny] == SNAKE) {
				System.out.println(cnt+1);
				return;
			}
			
			if(map[nx][ny] == APPLE) {
				q.add(new Position(nx, ny));
				move(nx,ny,dir,cnt+1);
			} else {
				Position pos = q.poll();
				map[pos.x][pos.y] = BLANK;
				q.add(new Position(nx, ny));
				move(nx,ny,dir,cnt+1);
			}
		}
		
		public void run() throws IOException {
			input();
			q.add(new Position(1, 1));
			move(1,1,0,0);
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

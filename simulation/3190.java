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
	int x;
	char c;
	public Operation(int x, char c) {
		this.x = x;
		this.c = c;
	}
}

class Position {
	int row;
	int col;
	public Position(int r, int c) {
		this.row = r;
		this.col = c;
	}
}

class Task {
	private int N,K,L;
	private int dir;
	private int[][] map;
	private final int BLANK = 0, APPLE = 1, SNAKE = 2;
	private Queue<Operation> op = new LinkedList<Operation>();
	private Queue<Position> q1 = new LinkedList<Position>();
	private Queue<Position> q2 = new LinkedList<Position>();
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
		dir = 0; // 동서남북
	}
	
	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		st = getStringTokenizer();
		K = Integer.parseInt(st.nextToken());
		for(int i=0; i<K; i++) {
			st = getStringTokenizer();
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = APPLE;
		}
		st = getStringTokenizer();
		L = Integer.parseInt(st.nextToken());
		for(int i=0; i<L; i++) {
			st = getStringTokenizer();
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			op.add(new Operation(x, c));
		}
	}
	
	private void changeDir(char c) {
		// 동서남북
		if(dir == 0) {
			if(c == 'L') {
				dir = 3;
			} else {
				dir = 2; 
			}
		} else if(dir == 1) {
			if(c == 'L') {
				dir = 2;
			} else {
				dir = 3;
			}			
		} else if(dir == 2) {
			if(c == 'L') {
				dir = 0;
			} else {
				dir = 1;
			}
		} else {
			if(c == 'L') {
				dir = 1;
			} else {
				dir = 0;
			}
		}
	}
	
	// 벽이거나 자신의 몸이면 false
	private boolean check(int row, int col) {
		if(row<1 || row>N || col<1 || col>N) {
//			System.out.println("WALL");
			return false;
		}
		if(map[row][col] == SNAKE) {
//			System.out.println("SNAKE");
			return false;
		}
		
		// 벽도 아니고 몸도 아니면 true
		return true;
	}
	
	// 이동한다 (false면 끝)
	private boolean move(int dir, int row, int col) {
		int nr= -1;
		int nc= -1;
		if(dir == 0) {
			nr = row;
			nc = col+1;
		} else if(dir == 1) {
			nr = row;
			nc = col-1;
		} else if(dir == 2) {
			nr = row+1;
			nc = col;
		} else {
			nr = row-1;
			nc = col;
		}
		
		// 벽인지 몸통인지
		if(check(nr, nc)) {
			q2.add(new Position(nr, nc));
//			System.out.println(nr + " " + nc);
			while(!q1.isEmpty()) {
				if(map[nr][nc] != APPLE && q1.size() == 1) {
					Position pos = q1.poll();
					map[pos.row][pos.col] = BLANK; // 이걸 빼먹어서 엄청 걸림
					break;
				} else {
					q2.add(q1.poll());
				}
			}
			map[nr][nc] = SNAKE;
//			print();
			return true;
		}
		return false;
	}
	
	private void solution() {
		int time = 0;
		boolean flag = false;
		q1.add(new Position(1, 1)); // 첫 시작
		map[1][1] = SNAKE;
		
		while(!op.isEmpty()) {
			Operation o = op.poll();
			int x = o.x;
			char c = o.c;
			
			// 시작점을 0초라고 한다면,
			while(time < x) {
				time++; // 움직이는 순간 1초
				Position pos = q1.peek();
				if(!move(dir, pos.row, pos.col)) {
					System.out.println(time);
					flag = true;
					break;
				}
				copy(); // 큐를 복사하여 뱀의 위치를 표현
			}
			if(flag) break;
			changeDir(c);
		}
		
		// 명령어가 다 끝났으면 이제 계속 한 방향으로 이동.
		if(!flag) {
			while(true) {
				time++;
				Position pos = q1.peek();
				if(!move(dir, pos.row, pos.col)) {
					System.out.println(time);
					flag = true;
					break;
				}
				copy(); // 큐를 복사하여 뱀의 위치를 표현
			}
		}
	}
	
	private void copy() {
		q1.clear();
		while(!q2.isEmpty()) {
			Position pos = q2.peek();
			map[pos.row][pos.col] = SNAKE;
			q1.add(q2.poll());
		}
	}
	
	public void run() throws IOException {
		input();
		solution();
	}
	
	public void print() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
//		while(!q1.isEmpty()) {
//			Position pos = q1.poll();
//			System.out.println(pos.row + " " + pos.col);
//		}
		
//		while(!q2.isEmpty()) {
//			Position pos = q2.poll();
//			System.out.println(pos.row + " " + pos.col);
//		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

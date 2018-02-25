package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
};

class Operation {
	int idx;
	int op;
	int loop;
	public Operation(int i, int o, int l) {
		this.idx = i;
		this.op = o;
		this.loop = l;
	}
}

class Task {
	private int A,B;
	private int N,M;
	private int[][] map;
	private int[] dir; // 각 로봇이 보고있는 방향
	private int EAST=1, WEST=2, SOUTH=3, NORTH=4;
	private int L=1, R=2, F=3;
	private ArrayList<ArrayList<Operation>> list;
	private Node[] pos; // 각 로봇의 좌표를 나타낼 것
	private int count=0;
	private boolean flag = false;
	
	// input
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		B = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		map = new int[A+1][B+1];
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dir = new int[N+1];
		pos = new Node[N+1];
		// map에 로봇의 초기위치 입력
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			int b = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			pos[i] = new Node(A-a+1, b);
			map[A-a+1][b] = i;
			// 각 로봇들이 보고있는 방향 입력
			switch(d) {
				case 'E':
					dir[i] = EAST;
					break;
				case 'W':
					dir[i] = WEST;
					break;
				case 'S':
					dir[i] = SOUTH;
					break;
				case 'N':
					dir[i] = NORTH;
					break;
			}
		}
		// 각 로봇들이 갖고있는 명령어들
		list = new ArrayList<ArrayList<Operation>>();
		for(int i=0; i<=M; i++) list.add(new ArrayList<Operation>());
		for(int i=1; i<=M; i++) {
			st = getStringTokenizer();
			int a = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			int b = Integer.parseInt(st.nextToken());
			for(int j=0; j<b; j++) {
				switch(c) {
					case 'L':
						list.get(i).add(new Operation(a, L, b));
						break;
					case 'R':
						list.get(i).add(new Operation(a, R, b));
						break;
					case 'F':
						list.get(i).add(new Operation(a, F, b));
						break;
				}
			}
		}
	}
	/*
	 * 명령을 수행할 로봇 인덱스
	 */
	private void rotateLeft(int idx) {
		int d = dir[idx]; // 로봇이 보고있는 방향의 왼쪽 90도로 회전
		if(d == EAST) {
			dir[idx] = NORTH;
		} else if(d == WEST) {
			dir[idx] = SOUTH;
		} else if(d == SOUTH) {
			dir[idx] = EAST;
		} else {
			dir[idx] = WEST;
		}
	}
	
	/*
	 * 명령을 수행할 로봇 인덱스
	 */
	private void rotateRight(int idx) {
		int d = dir[idx]; // 로봇이 보고있는 방향의 오른쪽 90도로 회전
		if(d == EAST) {
			dir[idx] = SOUTH;
		} else if(d == WEST) {
			dir[idx] = NORTH;
		} else if(d == SOUTH) {
			dir[idx] = WEST;
		} else {
			dir[idx] = EAST;
		}		
	}
	
	/*
	 * 벽이면 false, 그렇지 않으면 true
	 */
	private boolean isWall(int x, int y, int dir) {
		if(x>=1 && x<=A && y>=1 && y<=B) return true;
		else return false;
	}
	
	private void move(int idx) {
		int d = dir[idx];
		int x = pos[idx].x;
		int y = pos[idx].y;

		int nx=0,ny=0;
		if(d == EAST) {
			nx = x;
			ny = y+1;
		} else if(d == WEST) {
			nx = x;
			ny = y-1;
		} else if(d == SOUTH) {
			nx = x+1;
			ny = y;
		} else {
			nx = x-1;
			ny = y;
		}
		
		if(!isWall(nx,ny,d)) {
			System.out.println("Robot " + idx + " crashes into the wall");
			flag = true;
			return;
		}
		
		if(map[nx][ny] == 0) {
			map[nx][ny] = map[x][y];
			map[x][y] = 0;
			pos[idx].x = nx;
			pos[idx].y = ny;
		} else {
			System.out.println("Robot " + idx + " crashes into robot " + map[nx][ny]);
			flag = true;
		}
	}
	
	private void solution() throws IOException {
		for(int i=1; i<=M; i++) {
			if(flag) break;
			for(Operation op : list.get(i)) {
				if(flag) break;
				int idx = op.idx;
				int operation = op.op;
				if(operation == L) {
					rotateLeft(idx);
				} else if(operation == R) {
					rotateRight(idx);
				} else {
					move(idx);
				}
			}
		}
		
		if(!flag) System.out.println("OK");
	}

	public void run() throws IOException {
		input();
		solution();
	}
	
	private void print() {
		for(int i=1; i<=A; i++) {
			for(int j=1; j<=B; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

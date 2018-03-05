package algo;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Position {
	int x;
	int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Task {
	private int N,M;
	private char[][] map;
	private final int[] X = {-1,1,0,0};
	private final int[] Y = {0,0,-1,1};
	private final int MAX = 11;
	private Position R;
	private Position B;
	private Position O;
	private int answer = MAX;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[N+1][M+1];
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
				if(map[i][j] == 'R') {
					R = new Position(i, j);
				} else if(map[i][j] == 'B') {
					B = new Position(i, j);
				} else if(map[i][j] == 'O') {
					O = new Position(i, j);
				}
			}
		}
	}
	
	private int reverse(int dir) {
		if(dir == 0) return 1;
		else if(dir == 1) return 0;
		else if(dir == 2) return 3;
		else return 2;
	}
	
	private void move(int dir, int cnt, char[][]map, boolean r, boolean b, Position R, Position B) {
		cnt++;
		if(cnt > 10) return;
		
		char[][] temp = new char[N+1][M+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		int first = 0; // 0은 빨강 먼저, 1은 파랑 먼저
		switch(dir) {
		case 0:
			if(R.x > B.x) first = 1;
			break;
		case 1:
			if(R.x < B.x) first = 1;
			break;
		case 2:
			if(R.y > B.y) first = 1;
			break;
		case 3:
			if(R.y < B.y) first = 1;
			break;
		}
		
		if(first == 0) {
			// 빨강 먼저 움직임
			while(true) {
				int nx = R.x + X[dir];
				int ny = R.y + Y[dir];
				if(temp[nx][ny] == '#') break;
				if(temp[nx][ny] == 'O') {
					temp[R.x][R.y] = '.';
					r = true;
					break;
				}
				temp[R.x][R.y]= '.';
				temp[nx][ny] = 'R';
				R = new Position(nx,ny);
			}
			while(true) {
				int nx = B.x + X[dir];
				int ny = B.y + Y[dir];
				if(temp[nx][ny] == '#' || temp[nx][ny] == 'R') break;
				if(temp[nx][ny] == 'O') {
					temp[B.x][B.y] = '.';
					b = true;
					break;
				}
				temp[B.x][B.y] = '.';
				temp[nx][ny] = 'B';
				B = new Position(nx, ny);
			}
		} else {
			while(true) {
				int nx = B.x + X[dir];
				int ny = B.y + Y[dir];
				if(temp[nx][ny] == '#') break;
				if(temp[nx][ny] == 'O') {
					temp[B.x][B.y] = '.';
					b = true;
					break;
				}
				temp[B.x][B.y] = '.';
				temp[nx][ny] = 'B';
				B = new Position(nx, ny);
			}
			while(true) {
				int nx = R.x + X[dir];
				int ny = R.y + Y[dir];
				if(temp[nx][ny] == '#' || temp[nx][ny] == 'B') break;
				if(temp[nx][ny] == 'O') {
					temp[R.x][R.y] = '.';
					r = true;
					break;
				}
				temp[R.x][R.y]= '.';
				temp[nx][ny] = 'R';
				R = new Position(nx,ny);
			}
		}
		
		if(r && b) return;
		if(!r && b) return;
		if(r && !b) {
			answer = Math.min(answer, cnt);
			return;
		}
		for(int i=0; i<4; i++) {
			if(i==dir || i==reverse(dir)) continue;
			move(i, cnt, temp, r, b, R, B);
		}
	}
	
	private void solution() {
		for(int i=0; i<4; i++) {
			move(i,0,map,false,false,R,B);
		}
	}
	
	public void run() throws IOException {
		input();
		solution();
		System.out.println(answer == MAX ? -1 : answer);
	}
	
	private void print() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
 
public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

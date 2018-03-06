package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Task {
	private int N,M;
	private char[][] map;
	private Node R;
	private Node B;
	private Node O;
	private final int[] X = {0,0,1,-1};
	private final int[] Y = {1,-1,0,0};
	private int answer = 0;
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
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
					R = new Node(i, j);
				} else if(map[i][j] == 'B') {
					B = new Node(i, j);
				} else if(map[i][j] == 'O') {
					O = new Node(i, j);
				}
			}
		}
	}
	
	private int reverseDir(int dir) {
		if(dir == 0) return 1;
		else if(dir == 1) return 0;
		else if(dir == 2) return 3;
		else return 2;
	}
	
	private boolean checkFirst(int dir, Node R, Node B) {
		boolean redFirst = false;
		if(dir == 0) {
			if(R.x != B.x || R.y > B.y) redFirst = true;
		} else if(dir == 1) {
			if(R.x != B.x || R.y < B.y) redFirst = true;
		} else if(dir == 2) {
			if(R.y != B.y || R.x > B.x) redFirst = true;
		} else {
			if(R.y != B.y || R.x < B.x) redFirst = true;
		}
		return redFirst;
	}
	
	private boolean checkArea(int x, int y) {
		if(x>=1 && x<=N && y>=1 && y<=M) return true;
		else return false;
	}
	
	private void solution(int dir, Node R, Node B, char[][] map, int cnt, boolean ro, boolean bo) {
		if(answer == 1) return;
		if(cnt >= 10) return;
		
		cnt++;
		boolean redFirst = checkFirst(dir, R, B);
		char[][] temp = new char[N+1][M+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		if(redFirst) {
			int nrx = R.x;
			int nry = R.y;
			while(true) {
				if(!checkArea(nrx + X[dir], nry + Y[dir])) break;
				if(temp[nrx + X[dir]][nry + Y[dir]] != '#') {
					nrx += X[dir];
					nry += Y[dir];
					if(temp[nrx][nry] == 'O') {
						ro = true;
						break;
					}
				} else {
					break;
				}
			}
			
			temp[R.x][R.y] = '.';
			if(temp[nrx][nry] != 'O')
				temp[nrx][nry] = 'R';
			R = new Node(nrx, nry);
			
			int nbx = B.x;
			int nby = B.y;
			while(true) {
				if(!checkArea(nbx + X[dir], nby + Y[dir])) break;
				if(temp[nbx + X[dir]][nby + Y[dir]] != '#' && temp[nbx + X[dir]][nby + Y[dir]] != 'R') {
					nbx += X[dir];
					nby += Y[dir];
					if(temp[nbx][nby] == 'O') {
						bo = true;
						break;
					}
				} else {
					break;
				}
			}
			
			temp[B.x][B.y] = '.';
			if(temp[nbx][nby] != 'O')
				temp[nbx][nby] = 'B';
			B = new Node(nbx, nby);
		} else {
			int nbx = B.x;
			int nby = B.y;
			while(true) {
				if(!checkArea(nbx + X[dir], nby + Y[dir])) break;
				if(temp[nbx + X[dir]][nby + Y[dir]] != '#') {
					nbx += X[dir];
					nby += Y[dir];
					if(temp[nbx][nby] == 'O') {
						bo = true;
						break;
					}
				} else {
					break;
				}
			}
			
			temp[B.x][B.y] = '.';
			if(temp[nbx][nby] != 'O')
				temp[nbx][nby] = 'B';
			B = new Node(nbx, nby);
			
			int nrx = R.x;
			int nry = R.y;
			while(true) {
				if(!checkArea(nrx + X[dir], nry + Y[dir])) break;
				if(temp[nrx + X[dir]][nry + Y[dir]] != '#' && temp[nrx + X[dir]][nry + Y[dir]] != 'B') {
					nrx += X[dir];
					nry += Y[dir];
					if(temp[nrx][nry] == 'O') {
						ro = true;
						break;
					}
				} else {
					break;
				}
			}
			
			temp[R.x][R.y] = '.';
			if(temp[nrx][nry] != 'O')
				temp[nrx][nry] = 'R';
			R = new Node(nrx, nry);
		}
		
		if(ro && bo) return;
		if(!ro && bo) return;
		if(ro && !bo) {
			answer = 1;
			return;
		}
		if(!ro && !bo) {
			for(int i=0; i<4; i++) {
				if(i == dir || i == reverseDir(dir)) continue;
				solution(i, R, B, temp, cnt, false, false);
			}
		}
	}
	
	public void run() throws IOException {
		input();
		for(int i=0; i<4; i++) {
			solution(i, R, B, map, 0, false, false);
		}
		System.out.println(answer);
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
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

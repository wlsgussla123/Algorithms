package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
	private int N=12, M=6;
	private int[][] map = new int[N+1][M+1];
	private final int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}}; 
	private boolean[][] visited = new boolean[N+1][M+1];
	private final int R=1, G=2, B=3, P=4, Y=5, BLANK=0;
	private int answer = 0;
	private boolean flag = false;
	private ArrayList<Node> list;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	public StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		visited = new boolean[N+1][M+1];
		flag = false;
	}
	
	// int형으로 맵핑
	private void input() throws IOException {
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=M; j++) {
				switch (input[j-1]) {
					case '.':
						map[i][j] = BLANK;
						break;
					case 'R':
						map[i][j] = R;
						break;
					case 'G':
						map[i][j] = G;
						break;
					case 'B':
						map[i][j] = B;
						break;
					case 'P':
						map[i][j] = P;
						break;
					case 'Y':
						map[i][j] = Y;
						break;
				}
			}
		}
	}
	
	// 연결된 뿌요의 개수를 카운팅하는 dfs
	private void dfs(int x, int y, int value) {
		for(int i=0; i<4; i++) {
			int nx = x + dirs[i][0];
			int ny = y + dirs[i][1];
			
			if(nx>=1 && nx<=N && ny>=1 && ny<=M && !visited[nx][ny]) {
				if(map[nx][ny] == value) {
					list.add(new Node(nx, ny));
					visited[nx][ny] = true;
					dfs(nx,ny,value);
				}
			}
		}
	}
	
	// 4개 이상의 연결된 뿌요를 찾기위한 메서드
	private void search() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				if(!visited[i][j] && map[i][j] != BLANK) {
					list = new ArrayList<Node>();
					dfs(i,j, map[i][j]);
					if(list.size() >= 4) {
						flag = true;
						for(Node u : list) {
							map[u.x][u.y] = BLANK;
						}
					}
				}
			}
		}		
	}

	private void move() {
		for(int i=1; i<=M; i++) {
			for(int j=N-1; j>=1; j--) {
				for(int k=N; k>j; k--) {
					if(map[j][i] != BLANK && map[k][i] == BLANK) {
						map[k][i] = map[j][i];
						map[j][i] = BLANK;
						break;
					}
				}
			}
		}
	}
	
//	// 뿌요들을 내린다.
//	private void move() {
//		boolean isDown = false;
//		for(int i=1; i<12; i++) {
//			for(int j=1; j<=6; j++) {
//				if(map[i][j] != BLANK && map[i+1][j] == BLANK) {
//					map[i+1][j] = map[i][j];
//					map[i][j] = BLANK;
//					isDown = true;
//				}
//			}
//		}
//		if(isDown) move();
//	}
	
	private void process() {
		while(true) {
			init();
			search(); // 4개 이상의 연결된 뿌요들이 있는지 탐색
			if(!flag) break;
			answer++; // 터뜨렸다면 연쇄작용이므로 ++
			move(); // 내려오게 한다.
		}
		System.out.println(answer);
	}

	public void run() throws IOException {
		input();
		process();
	}
	
	private void print() {
		for(int i=1; i<=12; i++) {
			for(int j=1; j<=6; j++) {
				System.out.print(map[i][j] + " ");
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

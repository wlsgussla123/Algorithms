package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private char[][] map;
	private int answer = 987654321;
	
	// input
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
			}
		}
	}
	
	private void print() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	private void repaint(int x, int y) {
		int a = 0;
		int b = 0;
		int cnt = 0;
		for(int i=x; i<=x+7; i++) {
			for(int j=y; j<=y+7; j++) {
				if((i+j)%2 == 0 && map[i][j] != 'B') {
					a++;
				}
				if((i+j)%2 != 0 && map[i][j] == 'B') {
					a++;
				}
				if((i+j)%2 == 0 && map[i][j] != 'W') {
					b++;
				}
				if((i+j)%2 != 0 && map[i][j] == 'W') {
					b++;
				}
			}
		}
		cnt = a > b ? b : a;
		if(answer > cnt) answer = cnt;
	}
	
	private void process() {
		int nx = 0;
		int ny = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				nx = i+7;
				ny = j+7;
				if(nx<=N && ny<=M) {
					repaint(i,j);
				}
			}
		}
		System.out.println(answer);
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

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
	private int answer = 0;
	private final int BLANK = 0;
	// input
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private void move(int dir, int[][] map) {
		/*
		 * 차례대로 동서남북 순으로
		 */
		if(dir == 0) {
			for(int i=1; i<=N; i++) {
				for(int j=N-1; j>=1; j--) {
					for(int k=N; k>j; k--) {
						if(map[i][k] == BLANK && map[i][j] != BLANK) {
							map[i][k] = map[i][j];
							map[i][j] = BLANK;
						}
					}
				}
			}
		} else if(dir == 1) {
			for(int i=1; i<=N; i++) {
				for(int j=2; j<=N; j++) {
					for(int k=1; k<j; k++) {
						if(map[i][k] == BLANK && map[i][j] != BLANK) {
							map[i][k] = map[i][j];
							map[i][j] = BLANK;
						}
					}
				}
			}
		} else if(dir == 2) {
			for(int i=1; i<=N; i++) {
				for(int j=N-1; j>=1; j--) {
					for(int k=N; k>j; k--) {
						if(map[k][i] == BLANK && map[j][i] != BLANK) {
							map[k][i] = map[j][i];
							map[j][i] = BLANK;
						}
					}
				}
			}
		} else {
			for(int i=1; i<=N; i++) {
				for(int j=2; j<=N; j++) {
					for(int k=1; k<j; k++) {
						if(map[k][i] == BLANK && map[j][i] != BLANK) {
							map[k][i] = map[j][i];
							map[j][i] = BLANK;
						}
					}
				}
			}
		}
	}
	
	private void sumBlock(int dir, int[][] map) {
		// 각 방향에 해당되는 것부터 계산하고, 그 다음에는 나머지를 다 계산한다.
		if(dir == 0) {
			for(int i=1; i<=N; i++) {
				for(int j=N; j>1; j--) {
					if(map[i][j] != BLANK && map[i][j] == map[i][j-1]) {
						map[i][j] += map[i][j-1];
						map[i][j-1] = BLANK;
					}
				}
			}
		} else if(dir == 1) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<N; j++) {
					if(map[i][j] != BLANK && map[i][j] == map[i][j+1]) {
						map[i][j] += map[i][j+1];
						map[i][j+1] = BLANK;
					}
				}
			}
		} else if(dir == 2) {
			for(int i=1; i<=N; i++) {
				for(int j=N; j>1; j--) {
					if(map[j][i] != BLANK && map[j][i] == map[j-1][i]) {
						map[j][i] += map[j-1][i];
						map[j-1][i] = BLANK;
					}
				}
			}
		} else {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<N; j++) {
					if(map[j][i] != BLANK && map[j][i] == map[j+1][i]) {
						map[j][i] += map[j+1][i];
						map[j+1][i] = BLANK;
					}
				}
			}
		}
	}
	
	private void operate(int dir, int cnt, int[][] map) {
		int[][] copy = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				copy[i][j] = map[i][j];
			}
		}
		
		move(dir, copy); // first, move blocks
		sumBlock(dir, copy); // second, sum
		move(dir, copy);
		
		if(cnt == 5) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(copy[i][j] > answer) answer = copy[i][j];
				}
			}
		} else {
			for(int i=0; i<4; i++) {
				if(i == 0) {
					operate(i, cnt+1, copy);
				} else if(i == 1) {
					operate(i, cnt+1, copy);
				} else if(i == 2) {
					operate(i, cnt+1, copy);
				} else {
					operate(i, cnt+1, copy);
				}
			}
		}
	}
	
	private void solution() {
		int[][] copy = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				copy[i][j] = map[i][j];
			}
		}
		// 동서남북
		for(int i=0; i<4; i++) {
			operate(i,1,copy);
		}
	}

	public void run() throws IOException {
		input();
		solution();
		System.out.println(answer);
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

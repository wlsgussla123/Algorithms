package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N,L;
	private int[][] map;
	private int answer = 0;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private void checkRow() {
		for(int i=1; i<=N; i++) {
			int prev = map[i][1];
			int j=2;
			boolean[] visited = new boolean[N+1];
			
			for(j=2; j<=N; j++) {
				int diff = Math.abs(map[i][j] - prev);
				boolean flag = true;
				
				// 높이차이가 1보다 크면 안 됨.
				if(diff > 1) break;
				else if(diff == 1) {
					// 나의 길이가 경사로를 놓을 수 있는 길이인가?
					if(prev > map[i][j]) {
						if(j+L-1 > N) {
							flag = false;
							break;
						}
						for(int k=j; k<j+L; k++) {
							if(map[i][j] != map[i][k] || visited[k]) {
								flag = false;
								break;
							}
							visited[k] = true;
						}
					} else {
						if(j-L == 0) {
							flag = false;
							break;
						}
						for(int k=j-1; k>=j-L; k--) {
							if(prev != map[i][k] || visited[k]) {
								flag = false;
								break;
							}
							visited[k] = true;
						}
					}
					// 경사로를 만들 수 없으면 이 행은 가능성 없다.
					if(!flag) break;
					else {
						prev = map[i][j];
					}
				} else if(diff == 0) {
					prev = map[i][j];
					continue;
				}
			}
			if(j==N+1) {
				answer++;
			}
		}
	}
	
	private void checkCol() {
		for(int i=1; i<=N; i++) {
			int prev = map[1][i];
			int j=2;
			boolean[] visited = new boolean[N+1];

			for(j=2; j<=N; j++) {
				int diff = Math.abs(prev - map[j][i]);
				boolean flag = true;
				
				if(diff == 0) {
					prev = map[j][i];
					continue;
				} else if(diff > 1) {
					break;
				} else if(diff == 1) {
					if(prev < map[j][i]) {
						if(j-L == 0) {
							flag = false;
							break;
						}
						for(int k=j-1; k>=j-L; k--) {
							if(prev != map[k][i] || visited[k]) {
								flag = false;
								break;
							}
							visited[k] = true;
						}
					} else {
						if(j+L-1 > N) {
							flag = false;
							break;
						}
						for(int k=j; k<j+L; k++) {
							if(map[j][i] != map[k][i] || visited[k]) {
								flag = false;
								break;
							}
							visited[k] = true;
						}
					}
					
					if(!flag) {
						break;
					}
					prev = map[j][i];
				}
			}
			
			if(j==N+1) {
				answer++;
			}
		}
	}
	
	public void run() throws IOException {
		input();
		checkRow();
		checkCol();
		System.out.println(answer);
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

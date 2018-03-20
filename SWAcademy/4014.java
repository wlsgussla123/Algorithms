package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N,X;
	private int[][] map;
	private int answer;
	private boolean[][] visited;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
		answer = 0;
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private boolean searchRow(int idx) {
		int prev = map[idx][1];
		for(int i=2; i<=N; i++) {
			if(prev == map[idx][i]) {
				prev = map[idx][i];
				continue;
			} else if(prev > map[idx][i]) {
				if(prev - map[idx][i] != 1) return false;
				int cnt = 1; // X와 같은지 용도
				for(int j=i+1; j<i+X; j++) {
					if(j>N || visited[idx][j]) break;
					if(map[idx][j] == map[idx][j-1]) {
						cnt++;
					} else {
						break;
					}
				}
				if(cnt != X) return false;
				
				// 중복이 안 되므로 기록.
				for(int j=i; j<i+X; j++) {
					visited[idx][j] = true;
				}
			} else if(prev < map[idx][i]){
				if(map[idx][i] - prev != 1) return false;
				int cnt = 0;
				for(int j=i-1; j>=i-X; j--) {
					if(j<1 || visited[idx][j]) break;
					if(map[idx][j] == prev) {
						cnt++;
					} else {
						break;
					}
				}
				
				if(cnt!= X) return false;
				
				for(int j=i-1; j>=i-X; j--) {
					visited[idx][j] = true;
				}
			}
			
			prev = map[idx][i];
		}
		
		return true;
	}
	
	private boolean searchCol(int idx) {
		int prev = map[1][idx];
		for(int i=2; i<=N; i++) {
			if(prev == map[i][idx]) {
				prev = map[i][idx];
				continue;
			} else if(prev > map[i][idx]) {
				if(prev - map[i][idx] != 1) return false;
				int cnt = 1; // X와 같은지 용도
				for(int j=i+1; j<i+X; j++) {
					if(j>N || visited[j][idx]) return false;
					if(map[j][idx] == map[j-1][idx]) {
						cnt++;
					} else {
						return false;
					}
				}
				
				if(cnt != X) return false;

				// 중복이 안 되므로 기록.
				for(int j=i; j<i+X; j++) {
					visited[j][idx] = true;
				}
			} else if(prev < map[i][idx]){
				if(map[i][idx] - prev != 1) return false;
				int cnt = 0;
				for(int j=i-1; j>=i-X; j--) {
					if(j<1 || visited[j][idx]) return false;
					if(map[j][idx] == prev) {
						cnt++;
					} else {
						return false;
					}
				}
				
				if(cnt!= X) return false;
				
				// 중복이 안 되므로 기록.
				for(int j=i-1; j>=i+X; j--) {
					visited[j][idx] = true;
				}
			}
			
			prev = map[i][idx];
		}
		return true;		
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			input();
			for(int i=1; i<=N; i++) {
				if(searchRow(i)) answer++;
			}
			visited = new boolean[N+1][N+1];
			for(int i=1; i<=N; i++) {
				if(searchCol(i)) answer++;
			}
			
			System.out.println("#"+t+" "+answer);
		}
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

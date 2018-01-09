package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[][] map;
	private int[][] nextMap;
	private int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	
	private boolean isContinue = true;
	private boolean[][] visited;
	private int iceburg = 0;
	private int answer = 0;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][M+1];
		nextMap = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
	}

	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				nextMap[i][j] = map[i][j];
			}
		}
	}

	// map을 순회하며 사방의 바다 개수만큼 빙산의 크기를 줄인다.
	private void thaw(int row, int col) {
		int cnt = 0;
		
		for(int i=0; i<4; i++) {
			int x = row + dirs[i][0];
			int y = col + dirs[i][1];
			
			if(x>=1 && x<=N && y>=1 && y<=M && map[x][y] == 0) {
				cnt++;
			}
		}

		nextMap[row][col] -= cnt;
		if(nextMap[row][col] < 0) nextMap[row][col] = 0;
	}
	
	// 빙산 조각을 카운팅 한다.
	private void checkIceburg(int row, int col, int iceburg) {
		visited[row][col] = true;
		
		for(int i=0; i<4; i++) {
			int x = row + dirs[i][0];
			int y = col + dirs[i][1];
			
			if(!visited[x][y] && x>=2 && x<=N-1 && y>=2 && y<=M-1 && nextMap[x][y] != 0) {
				visited[x][y] = true;
				checkIceburg(x, y, iceburg);
			}
		}
	}
	// 빙산을 체킹할 때 사용하는 방문 배열 초기화
	private void initVisit() {
		for(int i=2; i<=N-1; i++) {
			for(int j=2; j<=M-1; j++) {
				visited[i][j] = false;
			}
		}
	}
	
	// true면 map에 빙산이 있는 것, false면 없는 것 
	private boolean checkHasMap() {
		boolean check = false;
		for(int i=2; i<=N-1; i++) {
			for(int j=2; j<=M-1; j++) { 
				if(map[i][j] != 0) {
					check = true;
				}
			}
		}
		
		return check;
	}
	
	
	// nextMap에 있는 것으르 다시 map으로 옮긴다.
	private void copyMap(int[][] a, int[][] b) {
		for(int i=2; i<=N-1; i++) {
			for(int j=2; j<=M-1; j++) {
				a[i][j] = b[i][j];
			}
		}
	}
	
//	private void print() {
//		for(int i=1; i<=N; i++) {
//			for(int j=1; j<=M; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
//	}

	public void run() throws IOException {
		input();
		
		while(isContinue) {
			answer++; // 정답
			
			// 빙산 녹는다
			// 범위는 첫 번째 행과 열, 마지막 행과 열에는 항상 0이 들어오므로
			for(int i=2; i<=N-1; i++) {
				for(int j=2; j<=M-1; j++) {
					if(map[i][j] != 0) {
						thaw(i,j);					
					}
				}
			}
			copyMap(map, nextMap); // 빙산을 다음 빙산으로 셋팅

			// 빙산 개수 체크
			for(int i=2; i<=N-1; i++) {
				if(iceburg > 1) break;
				for(int j=2; j<=M-1; j++) {
					if(iceburg > 1) break;
					if(!visited[i][j] && nextMap[i][j] != 0) {
						checkIceburg(i, j, ++iceburg);
					}
				}
			}
//			// 아직 더 녹여야 하는가?
			if(iceburg > 1) break;
			iceburg = 0; // 빙산 개수를 체크하면서 +1이 되었으므로 다시 초기화
			initVisit(); // 방문 배열 초기화
			// 맵에 빙산이 더 없는데 iceburg가 2도 넘지 못 했으므로, 0이 출력되어야 한다.
			if(!checkHasMap()) {
				answer = 0;
				isContinue = false;
			}
		}
		System.out.println(answer);
		br.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

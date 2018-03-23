package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[][] map;
	private int answer = 0;
	private int[][][] dirs = {
			{{0,0}, {0,1}, {0,2}, {0,3}}, // -
			{{0,0}, {1,0}, {2,0}, {3,0}}, // ㅣ
			{{0,0}, {0,1}, {1,0}, {1,1}}, // ㅁ
			{{0,0}, {1,0}, {2,0}, {2,1}}, // ㄴ
			{{0,0}, {1,0}, {2,0}, {2,-1}}, // ㄴ 반대
			{{0,0}, {0,1}, {0,2}, {1,0}}, // ㄱ 반대
			{{0,0}, {0,1}, {0,2}, {1,2}}, // ㄱ
			{{0,0}, {0,1}, {1,0}, {2,0}}, // ㄱ반대 긴거
			{{0,0}, {0,1}, {1,1}, {2,1}}, // ㄱ 긴거
			{{0,0}, {1,0}, {1,1}, {1,2}}, // ㄴ긴거
			{{0,0}, {1,0}, {1,-1}, {1,-2}}, // ㄴ 반대 긴거
			{{0,0}, {1,0}, {1,1}, {2,1}}, // 번개
			{{0,0}, {1,0}, {1,-1}, {2,-1}}, // 번개 반대
			{{0,0}, {0,1}, {1,1}, {1,2}}, // z
			{{0,0}, {0,1}, {1,0}, {1,-1}}, // z 반대
			{{0,0}, {0,1}, {0,2}, {1,1}}, // ㅜ
			{{0,0}, {-1,0}, {1,0}, {0,-1}}, // ㅓ
			{{0,0}, {1,0}, {-1,0}, {0,1}}, // ㅏ
			{{0,0}, {0,1}, {0,2}, {-1,1}} // ㅗ
 	};
	
	private StringTokenizer st = null;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][M+1];
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private boolean checkArea(int x, int y) {
		return (x>=1 && x<=N && y>=1 && y<=M);
	}
	
	private void solution() throws IOException {
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				for(int k=0; k<19; k++) {
					cnt = 0;
					for(int l=0; l<4; l++) {
						int nx = i + dirs[k][l][0];
						int ny = j + dirs[k][l][1];
					
						if(checkArea(nx, ny)) {
							cnt += map[nx][ny];
						} else {
							cnt = 0;
							break;
						}
					}
					answer = answer > cnt ? answer : cnt;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	public void run() throws IOException {
		input();
		solution();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

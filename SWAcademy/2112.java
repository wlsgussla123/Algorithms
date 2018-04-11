package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M,K;
		private int[][] map;
		private int ans;
		private final int ZERO = 0, ONE = 1; // zero : a, one : b
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N][M];
			ans = 987654321;
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=0; i<N; i++) {
				st = getStringTokenizer();
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		// K개의 셀이 각 필름마다 존재하는가
		private boolean check(int[][] map) {
			int cnt = 1;
			boolean flag = false;
			for(int j=0; j<M; j++) {
				cnt = 1;
				flag = false;
				for(int i=0; i<N-1; i++) {
					if(map[i][j] == map[i+1][j]) {
						cnt++;
					} else {
						cnt = 1;
					}

					if(cnt == K) {
						flag = true;
						break;
					}
				}
				if(!flag) return false;
			}
			
			return true;
		}
		
		private void changeType(int idx, int type, int[][] map) {
			for(int i=0; i<M; i++) {
				map[idx][i] = type;
			}
		}
		
		private void solution(int idx, int type, int cnt, int[][] map) {
			if(ans <= cnt) return;
			int[][] temp = new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					temp[i][j] = map[i][j];
				}
			}
			
			changeType(idx, type, temp);
			if(check(temp)) {
				ans = cnt;
				return;
			}
			
			for(int i=idx+1; i<N; i++) {
				solution(i, ZERO, cnt+1, temp);
				solution(i, ONE, cnt+1, temp);
			}
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int idx=1; idx<=T; idx++) {
				input();
				if(check(map)) {
					bw.write("#" + idx + " 0\n");
				} else {
					for(int i=0; i<N; i++) {
						solution(i, ZERO, 1, map);
						solution(i, ONE, 1, map);
					}
					bw.write("#" + idx + " " + String.valueOf(ans)+"\n");
				}
			}
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

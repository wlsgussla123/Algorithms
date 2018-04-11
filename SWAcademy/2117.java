package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Position {
	int x;
	int y;
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int[][] map;
		private ArrayList<Position> list;
		private int N,M;
		private int max;
		private int ans;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			map = new int[N+1][N+1];
			list = new ArrayList();
			ans = 0;
			max = 0;
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=1; i<=N; i++) {
				st = getStringTokenizer();
				for(int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						list.add(new Position(i, j));
						map[i][j] = 0;
					}
				}
			}
		}
		
		private boolean checkArea(int x, int y) {
			return x>=1 && x<=N && y>=1 && y<=N;
		}
		
		// 각 좌표마다 방범창을 설치하기 위한 함수
		private void takeService(int x, int y, int k) {
			int idx = 0;
			for(int i=x; i<x+k; i++) {
				for(int j=y; j<y+k-idx; j++) {
					if(!checkArea(i, j)) break;
					map[i][j] = 1;
				}
				
				for(int j=y; j>y-k+idx; j--) {
					if(!checkArea(i, j)) break;
					map[i][j] = 1;
				}
				idx++;
			}
			
			idx = 0;
			for(int i=x; i>x-k; i--) {
				for(int j=y; j<y+k-idx; j++) {
					if(!checkArea(i, j)) break;
					map[i][j] = 1;
				}
				
				for(int j=y; j>y-k+idx; j--) {
					if(!checkArea(i, j)) break;
					map[i][j] = 1;
				}
				idx++;				
			}
		}
		
		// 손익 계산기
		private boolean isLoss(int k) {
			int cnt = 0;
			for(Position pos : list) {
				if(map[pos.x][pos.y] == 1) {
					cnt++;
				}
			}
			max = cnt;
			
			if((k*k + (k-1) * (k-1)) - cnt * M > 0) {
				return false;
			} else {
				return true;
			}
		}
		
		public void run() throws IOException {
			st = getStringTokenizer();
			int T = Integer.parseInt(st.nextToken());
			for(int idx=1; idx<=T; idx++) {
				input();
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						for(int k=1; k<=N*2; k++) {
							takeService(i, j, k);
							if(isLoss(k)) {
								ans = ans > max ? ans : max;
							}
							clear();
						}
					}
				}
				bw.write("#"+idx + " " +String.valueOf(ans)+"\n");
			}
			close();
		}
		
		private void clear() {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					map[i][j] = 0;
				}
			}
		}
		
		private void print() {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

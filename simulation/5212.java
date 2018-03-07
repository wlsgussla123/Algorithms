package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private char[][] map;
	private char[][] answer_map;
	private int[] X = {0,0,1,-1};
	private int[] Y = {1,-1,0,0};
	private int rs,re,cs,ce;
	
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N+1][M+1];
		answer_map = new char[N+1][M+1];
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=M; j++) {
				map[i][j] = input[j-1];
			}
		}
	}
	
	private void solution() {
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				answer_map[i][j] = map[i][j];
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				cnt = 0;
				for(int k=0; k<4; k++) {
					int nx = i + X[k];
					int ny = j + Y[k];
					
					if(nx<1 || nx>N || ny<1 || ny>M) {
						cnt++;
						continue;
					} else {
						if(map[nx][ny] == '.')
							cnt++;
					}
				}
				if(cnt >= 3) answer_map[i][j] = '.';
			}
		}
		
		// 구간 설정
		boolean flag;
		int i=1, j=1;
		for(i=1; i<=N; i++) {
			flag = false;
			for(j=1; j<=M; j++) {
				if(answer_map[i][j] == 'X') {
					flag = true;
					break;
				}
			}
			if(flag) break;
		}
		rs = i;
		
		for(i=N; i>=1; i--) {
			flag = false;
			for(j=1; j<=M; j++) {
				if(answer_map[i][j] == 'X') {
					flag = true;
					break;
				}
			}
			if(flag) break;
		}
		re = i;
		
		for(i=1; i<=M; i++) {
			flag = false;
			for(j=1; j<=N; j++) {
				if(answer_map[j][i] == 'X') {
					flag = true;
					break;
				}
			}
			if(flag) break;
		}
		cs = i;

		for(i=M; i>=1; i--) {
			flag = false;
			for(j=1; j<=N; j++) {
				if(answer_map[j][i] == 'X') {
					flag = true;
					break;
				}
			}
			if(flag) break;
		}
		ce = i;
	}
	
	public void run() throws IOException {
		input();
		solution();
		print();
	}
	
	private void print() {
		for(int i=rs; i<=re; i++) {
			for(int j=cs; j<=ce; j++) {
				System.out.print(answer_map[i][j]);
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

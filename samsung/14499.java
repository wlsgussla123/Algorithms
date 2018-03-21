package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,M,X,Y,K;
	private int[][] map;
	private int[] dice = {0,0,0,0,0,0};
	private Queue<Integer> order = new LinkedList<>();
	// 동서북남
	private int[] dx = {0,0,-1,1};
	private int[] dy = {1,-1,0,0};
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N][M];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = getStringTokenizer();
		for(int i=0; i<K; i++) {
			order.add(Integer.parseInt(st.nextToken()));
		}
	}
	
	private boolean checkArea(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<M);
	}
	
	// 1: 동, 2: 서, 3: 북, 4: 남
	private void roll(int dir) {
		int[] temp = new int[6];
		for(int i=0; i<6; i++) temp[i] = dice[i];
		
		if(dir == 1) {
			temp[0] = dice[3];
			temp[2] = dice[0];
			temp[5] = dice[2];
			temp[3] = dice[5];
		} else if(dir == 2) {
			temp[0] = dice[2];
			temp[2] = dice[5];
			temp[5] = dice[3];
			temp[3] = dice[0];
		} else if(dir == 3) {
			temp[0] = dice[4];
			temp[1] = dice[0];
			temp[5] = dice[1];
			temp[4] = dice[5];
		} else {
			temp[0] = dice[1];
			temp[1] = dice[5];
			temp[5] = dice[4];
			temp[4] = dice[0];
		}
		
		for(int i=0; i<6; i++) dice[i] = temp[i];
	}
	
	private void solution(int dir) {
		int nx = X + dx[dir-1];
		int ny = Y + dy[dir-1];
		if(!checkArea(nx, ny)) return;
		
		X = nx;
		Y = ny;
		// 굴리고
		roll(dir);
		// map에 대한 행위
		if(map[nx][ny] == 0) {
			map[nx][ny] = dice[5];
		} else {
			dice[5] = map[nx][ny];
			map[nx][ny] = 0;
		}
		System.out.println(dice[0]);
	}
	
	public void run() throws IOException {
		input();
		while(!order.isEmpty()) {
			int d = order.poll();
			solution(d);
		}
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

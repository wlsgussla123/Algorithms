package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Task {
	private final int N = 8;
	private int M;
	private int kx,ky,sx,sy;
	private final int[] dx = {0,0,1,-1,-1,-1,1,1};
	private final int[] dy = {1,-1,0,0,1,-1,1,-1};
	private ArrayList<String> list = new ArrayList<>();
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		char[] king = st.nextToken().toCharArray();
		int y = king[0] - 'A' + 1;
		int x = king[1] - '0';
		kx = N-x+1;
		ky = y;
		char[] stone = st.nextToken().toCharArray();
		y = stone[0] - 'A' + 1;
		x = stone[1] - '0';
		sx = N-x+1;
		sy = y;
		M = Integer.parseInt(st.nextToken());
		for(int i=0; i<M; i++) {
			st = getStringTokenizer();
			list.add(st.nextToken());
		}
	}
	
	private boolean checkArea(int x, int y) {
		return (x>=1 && x<=N && y>=1 && y<=N);
	}
	
	private void move(int dir) {
		// 각 좌표를 구하고
		int nkx = kx + dx[dir];
		int nky = ky + dy[dir];
		int nsx = sx;
		int nsy = sy;
		if(nkx == sx && nky == sy) {
			nsx = sx + dx[dir];
			nsy = sy + dy[dir];
		}
		
		if(!checkArea(nkx, nky) || !checkArea(nsx, nsy)) return;
		kx = nkx;
		ky = nky;
		sx = nsx;
		sy = nsy;
	}
	
	private void solution() {
		for(String op : list) {
			int dir = -1;
			if(op.equals("R")) {
				dir = 0;
			} else if(op.equals("L")) {
				dir = 1;
			} else if(op.equals("B")) {
				dir = 2;
			} else if(op.equals("T")) {
				dir = 3;
			} else if(op.equals("RT")) {
				dir = 4;
			} else if(op.equals("LT")) {
				dir = 5;
			} else if(op.equals("RB")) {
				dir = 6;
			} else if(op.equals("LB")) {
				dir = 7;
			}
			
			move(dir);
		}
		printAnswer();
	}
	
	private void printAnswer() {
		// 형식에 맞춰서 변환
		char[] kPos = new char[2];
		char[] sPos = new char[2];
		kPos[0] = (char) (ky-1 + 'A');
		kPos[1] = (char) (8-kx+1 + '0');
		sPos[0] = (char) (sy-1 + 'A');
		sPos[1] = (char) (8-sx+1 + '0');
		System.out.println(String.valueOf(kPos));
		System.out.println(String.valueOf(sPos));		
	}
	
	public void run() throws IOException {
		input();
		solution();
		close();
	}
	
	private void close() throws IOException {
		bw.close();
		br.close();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

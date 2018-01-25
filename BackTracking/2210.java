package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Task {
	private int[][] map = new int[6][6];
	private boolean[][] visited = new boolean[6][6];
	private int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
	private HashSet<Integer> set = new HashSet<Integer>();
	private int answer = 0;
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		for(int i=1; i<=5; i++) {
			st = getStringTokenizer();
			for(int j=1; j<=5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private void backtracking(int row, int col, StringBuffer num, int cnt) {
		if(cnt == 6) {
			int n = Integer.parseInt(num.toString());
			if(!set.contains(n)) set.add(n);
		} else {
			for(int i=0; i<4; i++) {
				int nx = row + dirs[i][0];
				int ny = col + dirs[i][1];
				
				if(nx>=1 && nx<=5 && ny>=1 && ny<=5) {
					StringBuffer sb = new StringBuffer();
					sb.append(num.toString());
					sb.append(map[nx][ny]);
					backtracking(nx, ny, sb, cnt+1);
				}
			}
		}
	}
	
	public void run() throws IOException {
		input();
		for(int i=1; i<=5; i++) {
			for(int j=1; j<=5; j++) {
				StringBuffer sb = new StringBuffer();
				sb.append(map[i][j]);
				backtracking(i, j, sb, 1);
			}
		}
		System.out.println(set.size());
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

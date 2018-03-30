package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[][] map;
	private boolean[][] visited;
	private final int[] dx = {0,0,1,-1};
	private final int[] dy = {1,-1,0,0};
	private boolean flag = false;
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=M; j++) {
				map[i][j] = input[j-1] - '0';
			}
		}
	}
	
	private boolean checkArea(int x, int y) {
		return x>=1 && x<=N && y>=1 && y<=M;
	}
	
	private void dfs(int x, int y) {
		if(x==N) {
			flag = true;
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!checkArea(nx, ny) || visited[nx][ny]) continue;
			if(map[nx][ny] == 1) continue; 
			visited[nx][ny] = true;
			dfs(nx,ny);
			if(flag) return;
		}
	}
	
	public void run() throws IOException {
		input();
		for(int i=1; i<=M; i++) {
			visited = new boolean[N+1][M+1];
			visited[1][i] = true;
			dfs(1,i);
			if(flag) break;
		}
		if(flag) bw.write("YES");
		else bw.write("NO");
		close();
	}
	
	private void close() throws IOException {
		bw.close();
		br.close();
	}
	
	private void print() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

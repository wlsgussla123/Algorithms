package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[][] map;
	private boolean[][] visited;
	private final int[] dx = {0,0,1,-1};
	private final int[] dy = {1,-1,0,0};
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N][N];
		visited = new boolean[N][N];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=0; j<N; j++) {
				map[i][j] = input[j] -'0';
			}
		}
	}
	
	private boolean checkArea(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
	
	private void dfs(int x, int y, int cnt) {
		map[x][y] = cnt;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(checkArea(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
				visited[nx][ny] = true;
				dfs(nx,ny,cnt);
			}
		}
	}
	
	private void solution() {
		int cnt = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					dfs(i,j,cnt++);
				}
			}
		}
		
		System.out.println(--cnt);
		int[] answer = new int[cnt+1];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 0) continue;
				answer[map[i][j]]++;
			}
		}
		Arrays.sort(answer);
		for(int i=1; i<=cnt; i++) {
			System.out.println(answer[i]);
		}
	}
	
	public void run() throws IOException {
		input();
		solution();
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

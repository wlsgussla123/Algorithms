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
	private int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
	private int answerCnt = 0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			String input = st.nextToken();
			for(int j=1; j<=N; j++) {
				map[i][j] = input.charAt(j-1) - '0';
			}
		}
	}

	private void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void dfs(int x, int y, int cnt) {
		map[x][y] = cnt;
		for(int i=0; i<4; i++) {
			int nx = x + dirs[i][0];
			int ny = y + dirs[i][1];
			
			if(nx>=1 && nx<=N && ny>=1 && ny<=N && !visited[nx][ny] && map[nx][ny]==1) {
				visited[nx][ny] = true;
				dfs(nx,ny,cnt);
			}
		}
	}
	
	private void count() {
		int[] arr = new int[answerCnt+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j]!=0) {
					arr[map[i][j]]++;
				}
			}
		}
		System.out.println(answerCnt);
		Arrays.sort(arr);
		for(int i=1; i<=answerCnt; i++) {
			System.out.println(arr[i]);
		}
	}
	
	private void process() {
		int cnt = 1;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(!visited[i][j] && map[i][j]==1) {
					visited[i][j] = true;
					dfs(i,j,cnt++);
				}
			}
		}
		answerCnt = cnt-1;
		count();
	}

	public void run() throws IOException {
		input();
		process();
		close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

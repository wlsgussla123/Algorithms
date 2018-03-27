package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[][] map;
	private boolean[][] visited;
	private int answer = 10000;
	private final int[] dx = {0,0,1,-1};
	private final int[] dy = {1,-1,0,0};
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private boolean checkArea(int x, int y) {
		return (x>=1 && x<=N && y>=1 && y<=N);
	}
	
	private void dfs(int x, int y, int sum, int cnt, boolean[][] visited) {
		boolean[][] temp = new boolean[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				temp[i][j] = visited[i][j];
			}
		}
		
		// 꽃길 중 하나라도 안 되면 취소하고, 꽃이 만개할 수 있다면 비용을 더하자.
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!checkArea(nx, ny) || temp[nx][ny]) return;
			temp[nx][ny] = true;
			sum += map[nx][ny];
		}
		
		if(cnt == 3) {
			answer = answer > sum ? sum : answer;
			return;
		}
		
		for(int i=2; i<=N-1; i++) {
			for(int j=2; j<=N-1; j++) {
				if(temp[i][j]) continue;
				temp[i][j] = true;
				dfs(i,j,sum+map[i][j],cnt+1,temp);
				temp[i][j] = false;
			}
		}
	}

	private void solution() {
		for(int i=2; i<=N-1; i++) {
			for(int j=2; j<=N-1; j++) {
				visited[i][j] = true;
				dfs(i,j,map[i][j],1,visited);
				visited[i][j] = false;
			}
		}
		System.out.println(answer);
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

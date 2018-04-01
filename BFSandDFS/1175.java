package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private char[][] map;
	private boolean[][][][][] visited;
	private final int IMPOSSIBLE = 987654321;
	private int answer = IMPOSSIBLE;
	
	private final int[] dx = {0,0,1,-1};
	private final int[] dy = {1,-1,0,0};
	private int sx = -1, sy = -1, ax = -1, ay = -1, bx = -1, by = -1;
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[N+1][M+1];
		visited = new boolean[N+1][M+1][4][2][2];
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
				map[i][j] = input[j-1];
				if(input[j-1] == 'S') {
					sx = i;
					sy = j;
				}
				if(input[j-1] == 'C' && ax == -1) {
					ax = i;
					ay = j;
				} else if(input[j-1] == 'C' && ax != -1) {
					bx = i;
					by = j;
				}
			}
		}
	}
	
	private boolean checkArea(int x, int y) {
		return x>=1 && x<=N && y>=1 && y<=M;
	}
	
	private void bfs(int sx, int sy) {
		Queue<int[]> q = new LinkedList<>();
		int[] arr = {sx,sy,-1,0,0,0};
		q.add(arr);
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int d = cur[2];
			int cnt = cur[3];
			int a = cur[4];
			int b = cur[5];
			
			if(x==ax && y==ay) {
				a=1;
			} else if(x==bx && y==by) {
				b=1;
			}
			
			if(a==1 && b==1) {
				answer = cnt;
				return;
			}
			
			for(int i=0; i<4; i++) {
				if(d==i) continue;
				
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(!checkArea(nx, ny) || map[nx][ny] == '#') continue;
				if(visited[nx][ny][i][a][b]) continue;
				visited[nx][ny][i][a][b] = true;
				int[] next = {nx,ny,i,cnt+1,a,b};
				q.add(next);
			}
		}
	}
	
	public void run() throws IOException {
		input();
		bfs(sx,sy);
		if(answer == IMPOSSIBLE) System.out.println("-1");
		else System.out.println(answer);
		close();
	}
	
	private void close() throws IOException {
		bw.close();
		br.close();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	new Task().run();
    }
}

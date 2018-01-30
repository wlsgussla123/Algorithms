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
	private int L,R,C;
	private char[][][] map;
	private int[][] dirs = {{1,0,0}, {-1,0,0}, {0,0,1}, {0,0,-1}, {0,1,0}, {0,-1,0}};
	private boolean[][][] visited;
	private int[] S = new int[3];
	private int[] E = new int[3];
	private int answer = 0;
	
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[L+1][R+1][C+1];
		visited = new boolean[L+1][R+1][C+1];
		answer = 0;
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		init();
		
		for(int i=1; i<=L; i++) {
			for(int j=1; j<=R; j++) {
				st = getStringTokenizer();
				char[] input = st.nextToken().toCharArray();
				for(int k=1; k<=C; k++) {
					map[i][j][k] = input[k-1];
					if(input[k-1] == 'S') {
						S[0] = i;
						S[1] = j;
						S[2] = k;
					} else if(input[k-1] == 'E') {
						E[0] = i;
						E[1] = j;
						E[2] = k;
					}
				}
			}
			st = getStringTokenizer();
		}
	}

	void close() throws IOException {
		br.close();
		bw.close();		
	}
	
	private void bfs(int z, int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {z,x,y,0};
		q.add(pos);
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cz = cur[0];
			int cx = cur[1];
			int cy = cur[2];
			int cc = cur[3];
			visited[cz][cx][cy] = true;
			
			if(cz == E[0] && cx == E[1] && cy == E[2]) {
				answer = cc;
				break;
			}
			
			for(int i=0; i<6; i++) {
				int nz = cz + dirs[i][0];
				int nx = cx + dirs[i][1];
				int ny = cy + dirs[i][2];
				
				if(nz>=1 && nz<=L && nx>=1 && nx<=R && ny>=1 && ny<=C && !visited[nz][nx][ny] && map[nz][nx][ny] != '#') {
					int[] next = {nz,nx,ny,cc+1};
					visited[nz][nx][ny] = true;
					q.add(next);
				}
			}
		}
		if(answer == 0) System.out.println("Trapped!");
		else System.out.println("Escaped in " + answer + " minute(s).");
	}
	
	public void run() throws IOException {
		while(true) {
			input();
			if(L == 0 && R == 0 && C == 0) break;
			bfs(S[0], S[1], S[2]);
		}
		close();			
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

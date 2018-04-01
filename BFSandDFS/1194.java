package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;
	int key;
	int cnt;
	
	public Node(int x, int y, int key, int cnt) {
		this.x = x;
		this.y = y;
		this.key = key;
		this.cnt = cnt;
	}
}

class Task {
	private int N,M;
	private char[][] map;
	private boolean[][][] visited;
	private int sx,sy;
	private final int[] dx = {0,0,1,-1};
	private final int[] dy = {1,-1,0,0};
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[N+1][M+1];
		visited = new boolean[N+1][M+1][1<<7];
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
				if(input[j-1] == '0') {
					sx = i;
					sy = j;
				}
			}
		}
	}
	
	private boolean checkArea(int x, int y) {
		return x>=1 && x<=N && y>=1 && y<=M;
	}
	
	private int bfs(int sx, int sy) {
		Queue<Node> q = new LinkedList<>();
		Node node = new Node(sx, sy, 0, 0);
		q.add(node);
		visited[sx][sy][0] = true;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int key = cur.key;
			int cnt = cur.cnt;
			
			if(map[x][y] == '1') {
				return cnt;
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nkey = key;
				if(!checkArea(nx, ny) || map[nx][ny] == '#') continue;
                if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f'){
                	nkey |= (1 <<(map[nx][ny]-'a'));
                }
                if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F'){
                    if((nkey & (1 << (map[nx][ny]-'A')))==0) continue;
                }
				if(visited[nx][ny][nkey]) continue;
				visited[nx][ny][nkey] = true;
				q.add(new Node(nx, ny, nkey, cnt+1));
			}
		}
		
		return -1;
	}
	
	public void run() throws IOException {
		input();
		int answer = bfs(sx,sy);
		if(answer == -1) bw.write("-1");
		else bw.write(String.valueOf(answer));
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

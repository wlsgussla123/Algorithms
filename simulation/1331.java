package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Task {
	private final int N = 6;
	private boolean[][] visited;
	private final int[] dx = {-1,-2,-2,-1,1,1,2,2};
	private final int[] dy = {-2,-1,1,2,2,-2,-1,1};
	private ArrayList<Node> list = new ArrayList<>();
	private int sx = 0, sy = 0;
	
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		visited = new boolean[N+1][N+1];
	}
	
	private void input() throws IOException {
		init();
		for(int i=0; i<36; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			int y = (input[0] - 'A') + 1;
			int x = input[1] - '0';
			list.add(new Node(x,y));
		}
	}
	
	private boolean checkArea(int x, int y) {
		return (x>=1 && x<=N && y>=1 && y<=N);
	}
	
	// 마지막 위치에서 처음으로 갈 수 있는가
	private boolean goFirst(int x, int y) {
		for(int i=0; i<8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!checkArea(nx, ny) || visited[nx][ny]) continue;
			if(nx == sx && ny == sy) return true;
		}
		
		return false; 
	}
	
	private void solution() {
		boolean flag = false;
		// 이전 좌표
		int px = list.get(0).x;
		int py = list.get(0).y;
		sx = px;
		sy = py;
		for(int i=1; i<list.size(); i++) {
			// 다음 좌표. (즉, 이전 좌표에서 다음좌표로 갈 수 있는가?)
			Node cur = list.get(i);
			int nx = cur.x;
			int ny = cur.y;
			flag = false;
			
			for(int j=0; j<8; j++) {
				// 체크하기 위한 좌표
				int cx = px + dx[j];
				int cy = py + dy[j];
				
				if(!checkArea(cx, cy)) continue;
				if(visited[cx][cy]) continue;					
				// 이전 좌표랑 같다?
				if(cx == nx && cy == ny) {
					visited[cx][cy] = true;
					px = cx;
					py = cy;
					flag = true; // 매칭 됐으면 flag = true
				}
			}
			// 다음으로 이동 못 했으면 끝내자
			if(!flag) {
				break;
			}
		}
		if(flag) {
			if(goFirst(px, py)) System.out.println("Valid");
			else System.out.println("Invalid");
		}
		else System.out.println("Invalid");
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

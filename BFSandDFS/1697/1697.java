package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Task {
	private int N,K;
	private boolean[] visited;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];
	}
	
	private void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		int[] pos = {N,0};
		q.add(pos);
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int X = cur[0];
			int cnt = cur[1];
			visited[X] = true; // 이미 방문했다는 것은 그 전에 왔으니까 걔가 더 빨리 이 위치에 도달할 수 있다는 뜻
			
			if(X == K) {
				System.out.println(cnt);
				break;
			}
			
			if(X-1 >= 0 && X-1 <= 100000 && !visited[X-1]) {
				int[] next = {X-1, cnt+1};
				visited[X-1] = true; // 방문하기 전에 방문 체킹을 해준다. 밑에도 동일. 어차피 얘가 더 빠름
				q.add(next);
			}

			if(X+1 >= 0 && X+1 <= 100000 && !visited[X+1]) {
				int[] next = {X+1, cnt+1};
				visited[X+1] = true;
				q.add(next);
			}

			if(X*2 >= 0 && X*2 <= 100000 && !visited[X*2]) {
				visited[X*2] = true;
				int[] next = {X*2, cnt+1};
				q.add(next);
			}
		}
	}

	public void run() throws IOException {
		input();
		bfs();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

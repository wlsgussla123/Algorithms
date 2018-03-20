package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Task {
	private int N;
	private int[][] map;
	private int answer = 987654321;
	private boolean[] visited;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N];
		for(int i=0; i<N; i++) {
			st = getStringTokenizer();
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private void solution(int idx, int cnt) {
		if(cnt == N/2) {
			int a=0, b=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(i==j) continue;
					if(visited[i] && visited[j]) {
						a += map[i][j];
					} else if(!visited[i] && !visited[j]){
						b += map[i][j];
					}
				}
			}
			
			answer = answer < Math.abs(a-b) ? answer : Math.abs(a-b);
			return;
		}
		
		for(int i=idx+1; i<N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			solution(i, cnt+1);
			visited[i] = false;
		}
	}
	
	public void run() throws IOException {
		input();
		visited[0] = true;
		for(int i=1; i<N; i++) {
			visited[i] = true;
			solution(i, 2); // 팀원 픽, 인원수
			visited[i] = false;
		}
		System.out.println(answer);
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

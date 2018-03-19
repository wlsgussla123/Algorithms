package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.temporal.IsoFields;
import java.util.StringTokenizer;

class Task {
	private boolean isContinue = true;
	private int N;
	private int[] number;
	private boolean[] visited;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		if(N==0) {
			isContinue = false;
			return;
		}
		number = new int[N];
		visited = new boolean[N];
		for(int i=0; i<N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private void dfs(int idx, int cnt) {
		if(cnt == 6) {
			for(int i=0; i<N; i++) {
				if(visited[i]) System.out.print(number[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=idx+1; i<N; i++) {
			visited[i] = true;
			dfs(i, cnt+1);
			visited[i] = false;
		}
	}
	
	public void run() throws IOException {
		while(isContinue) {
			input();
			for(int i=0; i<N; i++) {
				visited[i] = true;
				dfs(i,1);
				visited[i] = false;
			}
			System.out.println();
		}
	}
}

public class Main {
    public static void main(String a[]) throws IOException {
    	Task task = new Task();
    	task.run();
    }
}

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

class Task {
	private int[] number;
	private boolean[] visited;
	private int answer = 0;
	private int N,S;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		number = new int[N];
		visited = new boolean[N];
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	private void dfs(int idx, int sum) {
		if(sum == S) {
			answer++;
		}
		
		for(int i=idx+1; i<N; i++) {
			visited[i] = true;
			dfs(i, sum + number[i]);
			visited[i] = false;
		}
	}
	
	public void run() throws IOException {
		input();
		for(int i=0; i<N; i++) {
			visited[i] = true;
			dfs(i, number[i]);
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

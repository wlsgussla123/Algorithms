package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Task {
	private int N,S;
	private ArrayList<Integer> list = new ArrayList<>();
	private int answer = 0;
	private boolean[] visited;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st = null;
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		S = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		for(int i=0; i<N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
	}
	
	private void solution(int idx, int sum) {
		if(sum == S) answer++;
		if(idx+1 >= N) return;
		
		for(int i=idx+1; i<N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			solution(i, sum + list.get(i));
			visited[i] = false;
		}
	}
	
	public void run() throws IOException {
		input();
		for(int i=0; i<N; i++) {
			visited[i] = true;
			solution(i, list.get(i));
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

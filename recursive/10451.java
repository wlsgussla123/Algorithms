package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Task {
	private int T,N;
	private int[] permutation;
	private boolean[] visited;
	private int answer = 0;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		visited = new boolean[N+1];
		permutation = new int[N+1];
		answer = 0;
	}

	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		
		st = getStringTokenizer();
		for(int i=1; i<=N; i++) {
			permutation[i] = Integer.parseInt(st.nextToken());;
		}
	}
	
	private void permutationCycle(int index, int origin) {
		visited[index] = true;
		if(index == origin) {
			answer++;
			return;
		} else {
			permutationCycle(permutation[index], origin);
		}
	}

	public void run() throws IOException {
		st = getStringTokenizer();
		T = Integer.parseInt(st.nextToken());
		while(T-- > 0) {
			input();
			for(int i=1; i<=N; i++) {
				if(!visited[i]) {
					permutationCycle(permutation[i], i);
				}
			}
			System.out.println(answer);
		}

		br.close();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

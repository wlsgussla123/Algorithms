package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int N,M;
	private int[] books, students;
	private boolean[] visited;
	private int[][] adj;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	// N은 책, M은 학생
	private void init() {
		books = new int[N+1];
		students = new int[M+1];
		adj = new int[M+1][N+1];
	}

	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=M; i++) {
			st = getStringTokenizer();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 책의 범위를 입력받으면, 바로 A~B 책을 전부 학생과 매칭
			for(int j=a; j<=b; j++) {
				// adj[i] : students[i]와 인접한 그룹 books의 정점들
				// 학생이 원하는 번호와 매칭이므로 j = (a~b)
				adj[i][j] = j;
			}
		}
	}
	
	// 학생 그룹에 속한 정점 index를 이분 매칭시켜서 성공하면 true
	private boolean dfs(int index) {
		// 방문된 정점은 매칭 불가
		if(visited[index]) return false;
		visited[index] = true;
		for(int i=1; i<=N; i++) {
			if(adj[index][i] != 0) {
				// 책이 매칭된 것이 없거나 || 매칭되어 있었지만 원래 매칭되어 있던 정점을 다른 정점과 매칭시킬 수 있다면 
				if(books[i] == 0 || dfs(books[i])) {
					students[index] = i;
					books[i] = index;
					return true;
				}
			}
		}
		return false;
	}
	
	private void process() throws IOException {
		int match=0;
		// 학생을 책에 이분매칭 할 것 (book -> students)
		for(int i=1; i<=M; i++) {
			// 아직 매칭되지 않은 학생들의 그룹 정점에 대해 매칭 시도
			if(students[i] == 0) {
				// visited 배열 초기화
				visited = new boolean[M+1];
				if(dfs(i)) match++;				
			}
		}
		System.out.println(match);
	}

	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		while(T-->0) {
			input();
			process();			
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

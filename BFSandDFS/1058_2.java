package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Task {
	private int N;
	private char[][] map;
	private boolean[] visited;
	private boolean[] preVisited;
	private List<Integer>[] list;
	private int answer = 0;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		map = new char[N+1][N+1];
		visited = new boolean[N+1];
		preVisited = new boolean[N+1];
		list = new List[N+1];
		for(int i=1; i<=N; i++) list[i] = new ArrayList<>();
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		init();
		for(int i=1; i<=N; i++) {
			st = getStringTokenizer();
			char[] input = st.nextToken().toCharArray();
			for(int j=1; j<=N; j++) {
				map[i][j] = input[j-1];
				map[j][i] = input[j-1];
				if(input[j-1] == 'Y') {
					list[i].add(j);
				}
			}
		}
	}
	
	private void clear() {
		for(int i=1; i<=N; i++) {
			visited[i] = false;
			preVisited[i] = false;
		}
	}
	
	private void process() {
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			cnt = 0;
			preVisited[i] = true;
			for(Integer n : list[i]) {
				preVisited[n] = true;
				cnt++;
			}
			
			for(int j=1; j<=N; j++) {
				if(i==j) continue;
				if(preVisited[j]) {
					for(Integer m : list[j]) {
						if(preVisited[m]) continue;
						if(!visited[m]) {
							visited[m] = true;
							cnt++;
						}
					}
				}
			}
			if(cnt > answer) answer = cnt;
			clear();
		}
		System.out.println(answer);
	}

	public void run() throws IOException {
		input();
		process();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

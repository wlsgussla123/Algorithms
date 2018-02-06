package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

class Task {
	private int N,M;
//	private int[][] map;
	private List<Integer>[] list; 
	private boolean[] visited;
	private int[] hacking;
	// input
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st = null;

	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
//		map = new int[N+1][N+1];
		list = new List[N+1];
		for(int i=1; i<=N; i++) list[i] = new ArrayList<Integer>();
		visited = new boolean[N+1];
		hacking = new int[N+1];
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
//			map[b][a] = 1;
			list[b].add(a);
		}
	}
	
	private void clear() {
		for(int i=1; i<=N; i++) visited[i] = false;
	}
	
	private void hackCount(int index) {
		int cnt = 0;
		for(int i=1; i<=N; i++)
			if(visited[i])
				cnt++; 
		hacking[index] = cnt;
	}

	private void getAnswer() {
		int max = 0;
		for(int i=1; i<=N; i++) {
			if(hacking[i] > max) max = hacking[i];
		}
		for(int i=1; i<=N; i++) {
			if(hacking[i] == max) System.out.print(i + " ");
		}
	}
	
	private void dfs(int i) {
		for(Integer idx : list[i]) {
			if(!visited[idx]) {
				visited[idx] = true;
				dfs(idx);				
			}
		}
	}
	
	private void process() throws IOException {
		for(int i=1; i<=N; i++) {
			visited[i] = true;
			for(Integer idx : list[i]) {
				if(!visited[idx]) {
					visited[idx] = true;
					dfs(idx);
				}
			}
			hackCount(i);
			clear();
		}
		getAnswer();
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

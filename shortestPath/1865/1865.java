package algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Input {
	private File inFile;
	private BufferedReader br = null; // close는 Task 클래스에 할 것이다.
	
	public Input() {
		inFile = new File("C:\\dev\\algo\\algo\\src\\algo", "input.txt");
		try {
			br = new BufferedReader(new FileReader(inFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedReader getBufferedReader() {
		return this.br;
	}
}

class Task {
	private int[] dist;
	private List<int[]>[] graph;
	private int N,M,W;
	private int S,E,T;
	private final int INF = 987654321;
	private boolean negativeCycle = false;
	
	private BufferedReader br = null;
	private StringTokenizer st = null;
	private Input input = new Input();
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	@SuppressWarnings("unchecked")
	private void init() {
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<int[]>();
		}
		dist = new int[N+1];
		for(int i=1; i<=N; i++) dist[i] = INF;
		
		negativeCycle = false;
	}

	// 입력
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		init();
		
		// 도로
		for(int i=1; i<=M; i++) {
			st = getStringTokenizer();
			S = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			int[] edge = {E,T};
			graph[S].add(edge);
			int[] rEdge = {S,T}; // 도로는 무방향이므로 양방향
			graph[E].add(rEdge);
		}
		
		// 웜홀
		for(int i=1; i<=W; i++) {
			st = getStringTokenizer();
			S = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken()) * -1; // 웜홀은 반대로 음의 가중치
			int[] edge = {E,T};
			graph[S].add(edge);
		}
	}
	
	private void bellmanford() {
		dist[1] = 0; // 시작점 = 0
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				for(int[] edge : graph[j]) {
					int next = edge[0];
					int cost = edge[1];
					
					if(dist[j] != INF && dist[next] > dist[j] + cost) {
						dist[next] = dist[j] + cost;
						if(i==N) negativeCycle = true; // 음의 사이클
					}
				}
			}
		}
	}
	
	private void print() {
		if(negativeCycle) System.out.println("YES");
		else System.out.println("NO");
	}

	public void run() throws IOException {
		br = input.getBufferedReader();
		st = getStringTokenizer();
		int test = Integer.parseInt(st.nextToken());
		while(test-->0) {
			input();
			bellmanford();
			print();			
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

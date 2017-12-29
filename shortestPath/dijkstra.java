package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Task {
	private int[][] adj; // 인접행렬
	private int[] distance; // 거리
	private boolean[] visit; // 방문배열
	
	private int V;
	private int E;
	private int startV;
	
	private final int MAX = 987654321;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private StringTokenizer st;
	
	private void init() {
		adj = new int[V+1][V+1];
		for(int i=0; i<=V; i++) {
			for(int j=0; j<=V; j++) {
				adj[i][j] = MAX;
			}
			adj[i][i] = 0;
		}
		
		distance = new int[V+1];
		for(int i=0; i<=V; i++) {
			distance[i] = MAX;
		}
		
		visit = new boolean[V+1];
		for(int i=0; i<=V; i++) {
			visit[i] = false;
		}
	}
	
	private void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		init(); // 인접행렬, 방문배열, 거리 초기화
		
		st = new StringTokenizer(br.readLine(), " ");
		startV = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			adj[from][to] = cost;
		}

//		bw.write(Integer.toString(V) + " ");
//		bw.write(Integer.toString(E) + " ");
//		bw.write(Integer.toString(startV) + "\n");
//		
		br.close();
//		bw.flush();
//		bw.close();
	}
	
	private void dijkstra() {
		distance[startV] = 0; // 시작점은 거리 0부터
		visit[startV] = true;

		// 최소 거리에 있는 정점으로 가야함
		int min = MAX;
		int minIndex = startV;
		
		for(int i=1; i<=V; i++) {
			min = MAX;
			
			// 첫 시작 때는 다른 곳이 INF이기 때문에 startV로 시작하게 될 것
			for(int j=1; j<=V; j++) {
				// 방문한 적 없고, 가장 작은 값을 추출하기 위하여 (다익스트라는 어떠한 지점을 거쳐가면 반드시 더 가중치가 늘어난다는 개념을 기억하라.)
				if(!visit[j] && min > distance[j]) {
					min = distance[j];
					minIndex = j;
				}
			}
			
			visit[minIndex] = true;
			System.out.println("visit : " + minIndex);
			for(int k=1; k<=V; k++) {
				// 기존에 알던 거리보다 지금 알고있는 최소 거리부분을 거쳐서 가는게 더 가깝다면
				if(distance[k] > distance[minIndex] + adj[minIndex][k]) {
					distance[k] = distance[minIndex] + adj[minIndex][k];
				}
			}
		}
	}
	
	private void printAdj() {
		for(int i=1; i<=V; i++) {
			for(int j=1; j<=V; j++) {
				if(adj[i][j] != MAX) {
					System.out.print(adj[i][j] + " ");
				} else {
					System.out.print(0 + " "); // 가시성을 위해 임시로 0으로
				}
			}
			System.out.println();
		}
	}
	
	private void printDijkstra() {
		for(int i=1; i<=V; i++) {
			if(distance[i] == MAX) {
				System.out.println("INF");
			} else {
				System.out.println(distance[i]);
			}
		}
	}
	
	public void run() throws IOException {
		input();
		dijkstra();
		printDijkstra();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

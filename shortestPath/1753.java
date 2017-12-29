package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Task {
	private List<List<int[]>> map = new ArrayList<List<int[]>>();
	private int[] distance; // 거리
	
	private int V;
	private int E;
	private int startV;
	
	private final int MAX = 987654321;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	
	private void init() {
		for(int i=0; i<V+1; i++) {
			map.add(new ArrayList<int[]>());
		}
		
		distance = new int[V+1];
		for(int i=0; i<V+1; i++) {
			distance[i] = MAX;
		}
	}
	
	private void input() throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		init();
		
		st = new StringTokenizer(br.readLine(), " ");
		startV = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			int[] edge = {cost, to};
			map.get(from).add(edge);
		}
		br.close();
	}
	
	private void dijkstra() {
		PriorityQueue<int[]> headq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] > o2[1]) {
					return 1;
				} else if(o1[1] > o2[1]) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		distance[startV] = 0; // 시작점은 거리 0부터
		
		int[] cur = {0, startV};
		headq.add(cur);
		int curCost, curIndex, nextCost, nextIndex;
		
		while(!headq.isEmpty()) {
			cur = headq.poll();
			curCost = cur[0];
			curIndex = cur[1];
			for(int[] edge: map.get(curIndex)) {
				nextCost = edge[0];
				nextIndex = edge[1];
			
				if(distance[nextIndex] > curCost + nextCost) {
					distance[nextIndex] = curCost + nextCost;
					int[] nextEdge = {curCost + nextCost, nextIndex};
					headq.add(nextEdge);
				}
			}
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


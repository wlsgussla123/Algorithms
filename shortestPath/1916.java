package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Task {
	private List<List<int[]>> map = new ArrayList<List<int[]>>();
	private int[] distances;
	
	private int N;
	private int M;
	private int startV;
	private int arriveV;
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer st;
	private final int MAX = 0x7fffffff;
	
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void init() {
		for(int i=0; i<=N; i++) {
			map.add(new ArrayList<int[]>());
		}
		
		distances = new int[N+1];
		for(int i=0; i<=N; i++) {
			distances[i] = MAX;
		}
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		N = Integer.parseInt(st.nextToken());
		st = getStringTokenizer();
		M = Integer.parseInt(st.nextToken());
		
		init();

		int from, to, cost;
		for(int i=0; i<M; i++) {
			st = getStringTokenizer();

			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			int[] edge = {to, cost};
			map.get(from).add(edge);
		}
		
		st = getStringTokenizer();
		startV = Integer.parseInt(st.nextToken());
		arriveV = Integer.parseInt(st.nextToken());
		
		br.close();
	}
	
	private void dijkstra() {
		PriorityQueue<int[]> headq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		distances[startV] = 0;
		int[] cur = {startV, 0};
		headq.add(cur);
		
		int curIndex, curCost, nextIndex, nextCost;
		while(!headq.isEmpty()) {
			cur = headq.poll();
			curIndex = cur[0];
			curCost = cur[1];
			
			for(int[] edge : map.get(curIndex)) {
				nextIndex = edge[0];
				nextCost = edge[1];
				
				if(distances[nextIndex] >= curCost + nextCost) {
					distances[nextIndex] = curCost + nextCost;
					int[] nextEdge = {nextIndex, curCost + nextCost};
					headq.add(nextEdge);
				}
			}
		}
	}
	
	private void answer() {
		System.out.println(distances[arriveV]);			
	}
	
	public void run() throws IOException {
		input();
		dijkstra();
		answer();
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		Task task = new Task();
		task.run();
	}
}

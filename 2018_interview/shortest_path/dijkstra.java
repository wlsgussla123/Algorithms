package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//class Node {
//	int to;
//	int value;
//	
//	public Node(int to, int value) {
//		this.to = to;
//		this.value = value;
//	}
//}

public class DijkstraMain {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int V,E;
		private int start;
		private final int INF = 987654321;
		private int[] dist;
		private ArrayList<ArrayList<int[]>> list;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			dist = new int[V+1];
			Arrays.fill(dist, INF);
			list = new ArrayList();
			for(int i=0; i<=V; i++) {
				list.add(new ArrayList());
			}
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			init();
			
			st = getStringTokenizer();
			start = Integer.parseInt(st.nextToken());
			
			for(int i=1; i<=E; i++) {
				st = getStringTokenizer();
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				
				int[] next = {to, value};
				list.get(from).add(next);
			}
		}
		
		private void dijkstra(int start) throws IOException {
			dist[start] = 0;
			PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[1] - o2[1];
				}
			});
			int[] a = {start, 0};
			pq.add(a);
			
			while(!pq.isEmpty()) {
				int[] c = pq.poll();
				int cur = c[0];
				int value = c[1];
				
				for(int[] node : list.get(cur)) {
					int next_idx = node[0]; 
					int next_value = node[1];
					
					if(dist[next_idx] > value + next_value) {
						dist[next_idx] = value + next_value;
						int[] next = {next_idx, dist[next_idx]};
						pq.add(next);
					}
				}
			}
			
			for(int i=1; i<=V; i++) {
				if(dist[i] == INF)
					bw.write("INF\n");
				else
					bw.write(String.valueOf(dist[i])+"\n");
			}
		}
		
		public void run() throws IOException {
			input();
			dijkstra(start);
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

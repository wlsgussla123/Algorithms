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

class Node {
	int to;
	int cost;
	
	public Node(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}
}

public class Bellmanford {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M;
		private ArrayList<ArrayList<Node>> list;
		private final int INF = 987654321;
		private int[] dist;
		private boolean negativeCycle = false;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			dist = new int[N+1];
			Arrays.fill(dist, INF);
			list = new ArrayList();
			for(int i=0; i<=N; i++) {
				list.add(new ArrayList());
			}
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=1; i<=M; i++) {
				st = getStringTokenizer();
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				list.get(from).add(new Node(to, cost));
			}
		}
		
		private void bellmanford(int start) throws IOException {
			dist[start] = 0;
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					for(Node node : list.get(j)) {
						int next = node.to;
						int cost = node.cost;
						
						if(dist[j] != INF && dist[next] > dist[j] + cost) {
							dist[next] = dist[j] + cost;
							if(i==N) negativeCycle = true;
						}
					}
				}
			}
			
			if(negativeCycle) {
				bw.write("-1\n");
			} else {
				for(int i=2; i<=N; i++) {
					if(dist[i] == INF) {
						bw.write("-1\n");
					} else {
						bw.write(String.valueOf(dist[i])+"\n");
					}
				}
			}
		}
		
		public void run() throws IOException {
			input();
			bellmanford(1);
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

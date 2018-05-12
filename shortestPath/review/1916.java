package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

class Node {
	int src;
	int cost;
	public Node(int s, int c) {
		this.src = s;
		this.cost = c;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M;
		private ArrayList<ArrayList<Node>> list = new ArrayList<ArrayList<Node>>();
		private int A,B;
		private final int INF = 987654321;
		private int[] dist;
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			for(int i=0; i<=N; i++) {
				list.add(new ArrayList<Node>());
			}
			dist = new int[N+1];
			Arrays.fill(dist, INF);
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			st = getStringTokenizer();
			M = Integer.parseInt(st.nextToken());
			init();
			
			int from = 0, to = 0, cost = 0;
			for(int i=0; i<M; i++) {
				st = getStringTokenizer();
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());
				list.get(from).add(new Node(to, cost));
			}
			
			st = getStringTokenizer();
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
		}
		
		private void solve() throws IOException {
			dist[A] = 0;
			PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
				@Override
				public int compare(Node arg0, Node arg1) {
					return arg0.cost - arg1.cost;
				}
			});
			
			pq.add(new Node(A, 0));
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				for(Node node : list.get(cur.src)) {
					if(dist[node.src] > dist[cur.src] + node.cost) {
						dist[node.src] = dist[cur.src] + node.cost;
						pq.add(new Node(node.src, dist[node.src]));
					}
				}
			}
			
			bw.write(String.valueOf(dist[B])+"\n");
		}
		
		public void run() throws IOException {
			input();
			solve();
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

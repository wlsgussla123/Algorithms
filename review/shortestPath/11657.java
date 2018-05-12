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
		private ArrayList<ArrayList<Node>> list = new ArrayList();
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
				list.add(new ArrayList());
			}
			
			dist = new int[N+1];
			Arrays.fill(dist, INF);
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			
			int from, to, cost;
			for(int i=1; i<=M; i++) {
				st = getStringTokenizer();
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				cost = Integer.parseInt(st.nextToken());
				list.get(from).add(new Node(to, cost));
			}
		}
		
		private void solve() throws IOException {
			dist[1] = 0;
			
			boolean negative = false;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					for(Node node : list.get(j)) {
						if(dist[j] != INF && dist[node.src] > dist[j] + node.cost) {
							dist[node.src] = dist[j] + node.cost;
							if(i==N) negative = true;
						}
					}
				}
			}
			
			if(negative) {
				System.out.println(-1);
			} else {
				for(int i=2; i<=N; i++) {
					if(dist[i] == INF) System.out.println("-1");
					else System.out.println(dist[i]);
				}
			}
			
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

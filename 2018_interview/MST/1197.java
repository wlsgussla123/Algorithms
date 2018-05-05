package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Kruskal {
	int from;
	int to;
	int cost;
	public Kruskal(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int V,E;
		private int[] uf;
		private boolean[] visited;
		
		private PriorityQueue<Kruskal> pq = new PriorityQueue(new Comparator<Kruskal>() {
			@Override
			public int compare(Kruskal arg0, Kruskal arg1) {
				return arg0.cost - arg1.cost;
			}
		});
		
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private StringTokenizer st = null;
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			uf = new int[V+1];
			visited = new boolean[V+1];
			for(int i=1; i<=V; i++) {
				uf[i] = -1;
			}
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			init();
			
			for(int i=0; i<E; i++) {
				st = getStringTokenizer();
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				pq.add(new Kruskal(from, to, cost));
			}
		}
		
		private int find(int n) {
			if(uf[n] < 0) return n;
			return uf[n] = find(uf[n]);
		}
		
		private boolean merge(int a, int b) {
			a = find(a);
			b = find(b);
			if(a == b) return false;
			uf[a] = b;
			return true;
		}
		
		private void solve() throws IOException {
			int sum = 0;
			
			while(!pq.isEmpty()) {
				Kruskal cur = pq.poll();
				if(merge(cur.from, cur.to)) {
					sum += cur.cost;
				}
			}
			bw.write(String.valueOf(sum));
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

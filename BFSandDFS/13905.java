package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int to;
	int w;
	public Node(int to, int w) {
		this.to = to;
		this.w = w;
	}
}

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M;
		private int S,E;
		private ArrayList<ArrayList<Node>> list = new ArrayList();
		private int ans = 0;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void init() {
			for(int i=0; i<=N; i++) {
				list.add(new ArrayList());
			}
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			init();
			
			st = getStringTokenizer();
			S = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<M; i++) {
				st = getStringTokenizer();
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				list.get(from).add(new Node(to, w));
				list.get(to).add(new Node(from, w));
			}
		}
		
		private boolean bfs(int weight) {
			boolean[] visited = new boolean[N+1];
			Queue<Integer> q = new LinkedList();
			q.add(S);
			visited[S] = true;
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				if(cur == E) return true;
				for(Node node : list.get(cur)) {
					if(node.w >= weight && !visited[node.to]) {
						visited[node.to] = true;
						q.add(node.to);
					}
				}
			}
			
			return false;
		}
		
		private void solution() throws IOException {
			int left = 1;
			int right = 1000000;
			while(left<=right) {
				int mid = (left+right)/2;
				if(bfs(mid)) {
					ans = ans > mid ? ans : mid;
					left = mid+1;
				} else {
					right = mid-1;
				}
			}
			bw.write(String.valueOf(ans));
		}
		
		public void run() throws IOException {
			input();
			solution();
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

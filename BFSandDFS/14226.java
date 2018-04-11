package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
	int time;
	int cnt;
	int buf;
	public Node(int time, int cnt, int buf) {
		this.time = time;
		this.cnt = cnt;
		this.buf = buf;
	}
}

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int S;
		private boolean[][] visited;
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			S = Integer.parseInt(st.nextToken());
			visited = new boolean[1001][10000];
		}
		
		private void bfs() throws IOException {
			Queue<Node> q = new LinkedList();
			visited[1][0] = true;
			q.add(new Node(0,1,0));
			
			while(!q.isEmpty()) {
				Node cur = q.poll();
				int time = cur.time;
				int cnt = cur.cnt;
				int buf = cur.buf;
				
				if(cnt == S) {
					bw.write(String.valueOf(time));
					return;
				}
				
				q.add(new Node(time+1, cnt, cnt));
				if(buf > 0 && cnt+buf <= 1000) {
					if(!visited[cnt+buf][buf]) {
						visited[cnt+buf][buf] = true;
						q.add(new Node(time+1, cnt+buf, buf));
					}
				}
				if(cnt-1 >= 0) {
					if(!visited[cnt-1][buf]) {
						visited[cnt-1][buf] = true;
						q.add(new Node(time+1, cnt-1, buf));
					}
				}
			}
		}
		
		public void run() throws IOException {
			input();
			bfs();
			close();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}

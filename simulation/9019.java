package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int num;
	String ans;
	public Node(int n, String a) {
		this.num = n;
		this.ans = a;
	}
}

class Task {
	private int A,B;
	private boolean[] visited;
	private StringTokenizer st = null;
	private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private StringTokenizer getStringTokenizer() throws IOException {
		return new StringTokenizer(br.readLine(), " ");
	}
	
	private void input() throws IOException {
		st = getStringTokenizer();
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		visited = new boolean[10000];
	}
	
	private int D(int n) {
		return 2 * n % 10000; 
	}
	
	private int S(int n) {
		return n==0 ? 9999 : n-1;
	}
	
	private int L(int n) {
		return n % 1000 * 10 + n / 1000;
	}
	
	private int R(int n) {
		return n % 10 * 1000 + n/10;
	}
	
	private void bfs() throws IOException {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(A, ""));
		visited[A] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int num = cur.num;
			
			if(num == B) {
				bw.write(cur.ans+"\n");
				return;
			}
			
			int new_num = num;
			for(int i=0; i<4; i++) {
				new_num = num;
				if(i==0) {
					new_num = D(new_num);
					if(visited[new_num]) continue;
					visited[new_num] = true;
					q.add(new Node(new_num, cur.ans+"D"));
				} else if(i==1) {
					new_num = S(new_num);
					if(visited[new_num]) continue;
					visited[new_num] = true;
					q.add(new Node(new_num, cur.ans+"S"));
				} else if(i==2) {
					new_num = L(new_num);
					if(visited[new_num]) continue;
					visited[new_num] = true;
					q.add(new Node(new_num, cur.ans+"L"));
				} else {
					new_num = R(new_num);
					if(visited[new_num]) continue;
					visited[new_num] = true;
					q.add(new Node(new_num, cur.ans+"R"));
				}
			}
		}
	}
	
	public void run() throws IOException {
		st = getStringTokenizer();
		int T = Integer.parseInt(st.nextToken());
		for(int i=0; i<T; i++) {
			input();
			bfs();
		}
		close();
	}
	
	private void close() throws IOException {
		bw.close();
		br.close();
	}
}

public class Main {
    public static void main(String args[]) throws IOException {
    	new Task().run();
    }
}

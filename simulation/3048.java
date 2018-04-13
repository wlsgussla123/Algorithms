package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Node {
	int dir;
	char c;
	public Node(int dir, char c) {
		this.dir = dir;
		this.c = c;
	}
}

public class Main {
	public static void main(String args[]) throws IOException {
		new Task().run();
	}
	
	static class Task {
		private int N,M,T;
		private ArrayList<Node> ant = new ArrayList();
		
		private StringTokenizer st = null;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		private StringTokenizer getStringTokenizer() throws IOException {
			return new StringTokenizer(br.readLine(), " ");
		}
		
		private void input() throws IOException {
			st = getStringTokenizer();
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			st = getStringTokenizer();
			String str1 = st.nextToken();
			for(int i=0; i<N; i++) {
				ant.add(new Node(0, str1.charAt(i)));
			}
			Collections.reverse(ant);

			st = getStringTokenizer();
			String str2 = st.nextToken();
			for(int i=0; i<M; i++) {
				ant.add(new Node(1, str2.charAt(i)));
			}
			
			st = getStringTokenizer();
			T = Integer.parseInt(st.nextToken());
		}
		
		private void move() {
			int size = ant.size();
			ArrayList<Node> temp = new ArrayList();
			int idx = 0;
			while(idx<size) {
				if(idx+1 < size && ant.get(idx).dir == 0 && ant.get(idx).dir != ant.get(idx+1).dir) {
					Node t = new Node(ant.get(idx).dir, ant.get(idx).c);
					temp.add(new Node(ant.get(idx+1).dir, ant.get(idx+1).c));
					temp.add(new Node(t.dir, t.c));
					idx += 2;
				} else {
					temp.add(new Node(ant.get(idx).dir, ant.get(idx).c));
					idx++;
				}
			}
			
			ant.clear();
			ant.addAll(temp);
		}
		
		private void solution() {
			for(int i=0; i<T; i++) {
				move();
			}
			print();
		}
		
		public void run() throws IOException {
			input();
			solution();
			close();
		}
		
		private void print() {
			for(Node node : ant) {
				System.out.print(node.c);
			}
			System.out.println();
		}
		
		private void close() throws IOException {
			bw.close();
			br.close();
		}
	}
}
